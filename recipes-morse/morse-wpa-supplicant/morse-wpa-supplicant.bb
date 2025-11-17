DESCRIPTION = "Build and install Sub-One GHz Hostapd"

TAG_NAME = "1.15.3"
PV = "${TAG_NAME}+git${SRCPV}"
SRC_URI = "git://github.com/MorseMicro/hostap.git;protocol=https;branch=v1.15;tag=${TAG_NAME} \
           file://wpa-supplicant_s1g.sh \
           file://wpa_supplicant.conf \
           file://wpa_supplicant_s1g.conf-sane \
           file://99_wpa_supplicant_s1g \
           file://0001-OSP-85-Register-wpa_supplicant_s1g-on-unique-DBus-pa.patch \
           file://0002-dbus-systemd-migrate-to-wpa_supplicant_s1g-service-s.patch \
           "


LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=5ebcb90236d1ad640558c3d3cd3035df"


inherit autotools
DEPENDS += "dbus libnl openssl"
RDEPENDS_${PN} += "libnl openssl"

CFLAGS:append = " -I${STAGING_INCDIR}/libnl3/ -I${STAGING_INCDIR}/ -Wno-error=deprecated-declarations"
LDFLAGS:append = " -L${STAGING_LIBDIR}/"
LIBS:append = " -lnl-3 -lm -lpthread -lcrypto -lssl"

S = "${WORKDIR}/git"
B = "${S}/wpa_supplicant"

inherit pkgconfig systemd

EXTRA_OEMAKE = "'LIBDIR=${libdir}' 'INCDIR=${includedir}' 'BINDIR=${sbindir}'"

do_configure() {
    cp ./defconfig ./.config
    sed -i 's/^#CONFIG_CTRL_IFACE_DBUS_NEW=y/CONFIG_CTRL_IFACE_DBUS_NEW=y/' .config
    sed -i 's/install -D wpa_passphrase $(DESTDIR)\/$(BINDIR)\/wpa_passphrase/install -D wpa_passphrase_s1g $(DESTDIR)\/$(BINDIR)\/wpa_passphrase_s1g/' Makefile
}

do_compile() {
    oe_runmake MORSE_VERSION=rel_1_15_3_2025_Apr_16 -C .
}

do_install() {
    export BINDIR="/usr/sbin"
    export DESTDIR="${D}"

    install -d ${D}${sysconfdir}
	install -m 600 ${WORKDIR}/wpa_supplicant_s1g.conf-sane ${D}${sysconfdir}/wpa_supplicant_s1g.conf

	install -d ${D}${sysconfdir}/network/if-pre-up.d/
	install -d ${D}${sysconfdir}/network/if-post-down.d/
	install -d ${D}${sysconfdir}/network/if-down.d/
	install -m 755 ${WORKDIR}/wpa-supplicant_s1g.sh ${D}${sysconfdir}/network/if-pre-up.d/wpa-supplicant_s1g
	ln -sf ../if-pre-up.d/wpa-supplicant_s1g ${D}${sysconfdir}/network/if-post-down.d/wpa-supplicant_s1g

    install -d ${D}/${sysconfdir}/dbus-1/system.d
	install -m 644 ${S}/wpa_supplicant/dbus/dbus-wpa_supplicant_s1g.conf ${D}/${sysconfdir}/dbus-1/system.d
	install -d ${D}/${datadir}/dbus-1/system-services
	install -m 644 ${S}/wpa_supplicant/dbus/*.service ${D}/${datadir}/dbus-1/system-services

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -d ${D}/${systemd_system_unitdir}
		install -m 644 ${S}/wpa_supplicant/systemd/*.service ${D}/${systemd_system_unitdir}
	fi

	install -d ${D}/etc/default/volatiles
	install -m 0644 ${WORKDIR}/99_wpa_supplicant_s1g ${D}/etc/default/volatiles

    oe_runmake install
}

FILES:${PN} += "${datadir}/dbus-1/system-services/* ${systemd_system_unitdir}/*"

DESCRIPTION = "Deploy the firmware and Board Configuration File binaries"
LICENSE="CLOSED"
BCF_FILENAME = "bcf_mf15457.bin"
COUNTRY = "AU"
TAG_NAME = "mm8108-2.1.0"
SRC_URI = "git://github.com/MorseMicro/morse-firmware.git;protocol=https;branch=2.1;tag=${TAG_NAME}"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_install() {
	oe_runmake -C ${S} install TARGET_DIR=${D}${nonarch_base_libdir}/firmware/morse

	install -d ${D}${sysconfdir}/modprobe.d
	echo "# Auto-generated configuration" > ${D}${sysconfdir}/modprobe.d/morse.conf
	echo "options morse country=${COUNTRY}" >> ${D}${sysconfdir}/modprobe.d/morse.conf
	echo "options morse bcf=${BCF_FILENAME}" >> ${D}${sysconfdir}/modprobe.d/morse.conf
}

# Ignore warning about firmware not being ARM
INSANE_SKIP:${PN} += " arch already-stripped"
FILES:${PN} += "${nonarch_base_libdir}/firmware/morse/*"
SYSROOT_DIRS_IGNORE += "${nonarch_base_libdir}/firmware/morse"
#FILES:${PN} += "${nonarch_base_libdir}/firmware/morse/bcf_mf08651_us.bin ${nonarch_base_libdir}/firmware/morse/mm6108.bin"
#FILES:${PN} += "${nonarch_base_libdir}/firmware/morse/bcf_mf15457.bin ${nonarch_base_libdir}/firmware/morse/mm8108b2-rl.bin"

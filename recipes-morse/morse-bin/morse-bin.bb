DESCRIPTION = "Deploy the firmware and Board Configuration File binaries"
LICENSE="CLOSED"

BCF_FILENAME = "bcf_mf15457.bin"
COUNTRY = "AU"

SRC_URI = "https://github.com/MorseMicro/firmware_binaries/releases/download/v1.15.3/morsemicro-fw-rel_1_15_3_2025_Apr_16.tar;protocol=https;name=fw \
		https://github.com/MorseMicro/bcf_binaries/releases/download/v1.15.3/morsemicro-bcf-rel_1_15_3_2025_Apr_16.tar;protocol=https;name=bcf \
		file://bcf_mf15457.bin \
		file://mm8108b2-rl.bin"

SRC_URI[fw.md5sum] = "e73cbb4d8f754c358844a6b2a2ed4988"
SRC_URI[fw.sha256sum] = "de7e3e9f1b7b97aff9d63687c4d3e99fe2544fcfc7e5567df1ab0da194656469"

SRC_URI[bcf.md5sum] = "32880a70de6f68469bbba384d433f045"
SRC_URI[bcf.sha256sum] = "bd794c829b57a9049b67bdc9ec9015c5f51ebf7716836e6a37a7962fb5121ef3"

S = "${WORKDIR}"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/morse
	install -m 0644 ${S}/lib/firmware/morse/mm6108.bin ${D}${nonarch_base_libdir}/firmware/morse/mm6108.bin
	install -m 0644 ${S}/lib/firmware/morse/bcf_mf08651_us.bin ${D}${nonarch_base_libdir}/firmware/morse/bcf_mf08651_us.bin

	install -m 0644 ${S}/mm8108b2-rl.bin ${D}${nonarch_base_libdir}/firmware/morse/mm8108b2-rl.bin
	install -m 0644 ${S}/bcf_mf15457.bin ${D}${nonarch_base_libdir}/firmware/morse/bcf_mf15457.bin

	install -d ${D}${sysconfdir}/modprobe.d
	echo "# Auto-generated configuration" > ${D}${sysconfdir}/modprobe.d/morse.conf
	echo "options morse country=${COUNTRY}" >> ${D}${sysconfdir}/modprobe.d/morse.conf
	echo "options morse bcf=${BCF_FILENAME}" >> ${D}${sysconfdir}/modprobe.d/morse.conf
}

# Ignore warning about firmware not being ARM
INSANE_SKIP:${PN} = "arch"
FILES:${PN} += "${nonarch_base_libdir}/firmware/morse/bcf_mf08651_us.bin ${nonarch_base_libdir}/firmware/morse/mm6108.bin"
FILES:${PN} += "${nonarch_base_libdir}/firmware/morse/bcf_mf15457.bin ${nonarch_base_libdir}/firmware/morse/mm8108b2-rl.bin"

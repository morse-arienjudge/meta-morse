DESCRIPTION = "Build and install Morse Command Line"

TAG_NAME = "mm8108-2.1.0"
PV = "${TAG_NAME}+git${SRCPV}"
SRC_URI = "git://github.com/MorseMicro/morse_cli.git;protocol=https;branch=main;tag=${TAG_NAME}"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSING;md5=fb9bb67f7333939b51bb986dc3a6cdd1"


inherit autotools
DEPENDS += "libnl libusb1"
RDEPENDS_${PN} += "libnl libusb1"

CFLAGS:append = " -I${STAGING_INCDIR}/libnl3/ -Wno-unused-result -Wno-maybe-uninitialized -I${STAGING_INCDIR}/libusb-1.0" 
LDFLAGS:append = " -L${STAGING_LIBDIR}/ -lnl-3 -lnl-genl-3 -lusb-1.0"

S = "${WORKDIR}/git"
B = "${S}"

do_compile() {
    oe_runmake CONFIG_MORSE_TRANS_NL80211=1
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 morse_cli ${D}${bindir}/morse_cli
}

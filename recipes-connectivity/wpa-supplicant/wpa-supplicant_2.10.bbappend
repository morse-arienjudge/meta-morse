FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG:append = " openssl"

SRC_URI:append = " \
    file://defconfig \
"

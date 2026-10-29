FILESEXTRAPATHS:prepend := "${THISDIR}/:${THISDIR}/${PN}:"

SRC_URI:append = " file://0002-patch-configure-networkmanager-to-talk-to-2.patch"

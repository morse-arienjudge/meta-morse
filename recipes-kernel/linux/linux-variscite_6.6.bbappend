FILESEXTRAPATHS:prepend := "${THISDIR}/files/6.6/:"

SRC_URI += "file://0001-mac80211-mlme-s1g-ecsa-support.patch"
SRC_URI += "file://0002-mac80211-tx-s1g-AP-ecsa-support.patch"
SRC_URI += "file://0003-mac80211-IBSS-bridge-support.patch"
SRC_URI += "file://0004-mac80211-Mesh-support.patch"
SRC_URI += "file://0005-mmc-sdio-1.8v-support-quirk.patch"
SRC_URI += "file://0006-spi-Force-CS_HIGH-if-GPIO-descriptors-are-used.patch"
SRC_URI += "file://0007-spi-support-control-of-SPI-CS-pin-on-initialisation.patch"
SRC_URI += "file://0008-mac80211-implement-support-for-thin-LMAC-S1G-NDP-blo.patch"
SRC_URI += "file://0009-kernel-enable-dev-coredump-by-default.patch"
SRC_URI += "file://0010-imx6ul-var-dart-add-support-for-SPI-attached-Morse-M.patch"

SRC_URI += "file://spi_imx_module.cfg"
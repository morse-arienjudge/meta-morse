# Morse Micro HaLow patches for 6.6 kernels. Included from a per-kernel
# bbappend, which is responsible for adding files/6.6 to FILESEXTRAPATHS.

SRC_URI += " \
	file://0001-mac80211-mlme-s1g-ecsa-support.patch \
	file://0002-mac80211-tx-s1g-AP-ecsa-support.patch \
	file://0003-mac80211-IBSS-bridge-support.patch \
	file://0004-mac80211-Mesh-support.patch \
	file://0005-mmc-sdio-1.8v-support-quirk.patch \
	file://0006-spi-Force-CS_HIGH-if-GPIO-descriptors-are-used.patch \
	file://0007-spi-support-control-of-SPI-CS-pin-on-initialisation.patch \
	file://0008-mac80211-implement-support-for-thin-LMAC-S1G-NDP-blo.patch \
	file://0009-kernel-enable-dev-coredump-by-default.patch \
	file://0010-mac80211-introduce-modparam-to-configure-association.patch \
	file://0011-wifi-mac80211-Initialize-EWMA-fail-avg-to-1.patch \
	file://0012-wifi-nl80211-Introduce-the-channel-flag-IEEE80211_CH.patch \
	file://0013-Revert-Revert-pinctrl-bcm2835-Set-base-to-0-give-exp.patch \
	file://0014-mac80211-add-halow-specific-mesh-optimizations.patch \
	file://0015-cfg80211-add-preprocessor-flag-to-communicate-existe.patch \
	file://0016-imx6ul-var-dart-add-support-for-SPI-attached-Morse-M.patch \
	file://0017-Revert-LF-8808-spi-imx-fix-message-endian-convert-in.patch \
	file://0018-mac80211-return-RX_QUEUED-in-6.6-to-resolve-multi-ho.patch \
	file://0019-Revert-LF-6680-spi-imx-add-dma-support-for-target-mo.patch \
	file://0020-wifi-nl80211-allow-Probe-client-on-mesh-to-support-k.patch \
	file://0021-LF-15429-spi-imx-add-dynamic-burst-for-spi-sdma-mode.patch \
	file://0022-wifi-mac80211-rectify-ping-delay-MP-to-mesh-gate-aft.patch \
	file://0023-nl80211-Match-enum-nl80211_ext_feature_index-with-ke.patch \
	file://0024-spi-imx-restore-gfp-parameter-following-NXP-changes.patch \
	file://0025-spi-imx-force-use_dynamic_burst-true.patch \
"


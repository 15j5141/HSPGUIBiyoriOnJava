# HSPGUIBiyoriOnJava
HSP風のメソッドでJavaのGUI(Swing等)を勝手にやってくれる。

# 経緯
複雑な処理はオブジェクト指向のJavaで、GUI周りはHSPで、を実現したかった。

# 使い方
---
HSP hsp = new HSP();
hsp.mes("Hello World!")
---
基本こんな感じ。

# 対応状況(Ver.0.1)
## 命令
screen(サイズだけ)
mes(改行不可)
picload
pos
cls
color
button(表示だけ)
objsize
font(スタイル指定不可)
## 変数
ginfo_cx, ginfo_cy
ginfo_mesx, ginfo_mesy
ginfo_r, ginfo_g, ginfo_b

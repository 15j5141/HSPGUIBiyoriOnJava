# HSPGUIBiyoriOnJava
HSP風のメソッドでJavaのGUI(Swing等)を勝手にやってくれる。

# 経緯
JavaでGUIは初めてではとっつきにくく忘れるため、複雑な処理はオブジェクト指向のJavaで、GUI周りはHSPで、を実現したかった。

# 使い方
```
HSP hsp = new HSP();
hsp.mes("Hello World!")
```
基本こんな感じ。


# 対応状況(Ver.0.2)
## 命令
### 完成？
- ```pos(x, y)```
- ```objsize(width, height)```
- ```title(titleName)```
- ```line(x1, y1, x2, y2)```
  - x2,y2が小さい場合でも描画可能.
- ```pset(x, y)```
- ```boxf(x1, y1, x2, y2)```
  - x2,y2が小さい場合でも描画可能.
- ```circle(x1, y1, x2, y2, mode)```
  - x2,y2が小さい場合でも描画可能.
  - modeが0なら枠だけ, nullや1などそれ以外の値では塗りつぶす.
### とりあえず動く
- ```screen(0, width, height)```
  - サイズだけ対応.
- ```mes("Hello.")```
  - 文字列内改行コードによる改行不可.
- ```picload(path)```
  - 第2引数0でのウィンドウを画像サイズにする機能は未実装.
- ```font(fontName, size)```
  - 第3引数でスタイル指定も可能だが現状swingのfontスタイル値として解釈する.
- ```cls()```
  - カラー初期化実装忘れ.
- ```button("abc", function)```
  - ~~押しても無意味なただの飾り.~~
  - 第2引数のgotoラベル指定は厳しい為ここにメソッドを指定できるよう仕様にしたので実質gosub.
  - ex:
```
public class TestRun{
	public static main(String[] args){
		hsp.button("button1", TestRun::funcA);
		hsp.button("button2", new TestRun()::funcB);
		hsp.button("button3", (objID)->{System.out.println("id0="+objID);});// ラムダ式も勿論可能.
	}
	static void funcA(int objID){
		// process
		// int引数を指定するとオブジェIDが取得できる.
	}
	void funcB(){
		// process example
		System.exit(0);
	}
}
```

### 変数
- ginfo_cx, ginfo_cy
- ginfo_mesx, ginfo_mesy
- ginfo_r, ginfo_g, ginfo_b
- stat
  - button実行後にオブジェIDが入る.

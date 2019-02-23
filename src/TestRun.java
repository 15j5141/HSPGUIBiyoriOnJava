
public class TestRun {

	public static void main(String[] args) {
		HSP hsp=new HSP();
		hsp.screen(0, 400, 400);
		hsp.title("Ver.0.1");
		hsp.mes("1111");
		hsp.cls();
		hsp.mes("test");
		hsp.mes("あいうえお");
		hsp.font("メイリオ", 25);
		hsp.picload("./media/sample.png");
		hsp.color(0, 0, 255);
		hsp.mes("testes");
		hsp.pos(100, null);
		hsp.picload("./media/sample.png");
		hsp.pos(200, 20);
		hsp.button("button1", (objID)->{System.out.println("_id0="+objID);});
		System.out.println("id="+hsp.stat);
		hsp.objsize(150, 30);
		hsp.button("button2", TestRun::yyy);
		hsp.button("button2", TestRun::yyy);
		System.out.println("id="+hsp.stat);
		hsp.mes("aaa");
	}
	static void xxx() {
		System.exit(0);
	}
	static void yyy(int objID) {
		System.out.println("_id1="+objID);
	}

}

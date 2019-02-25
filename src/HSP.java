import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HSP extends JFrame{
	public static int OriginWidth = 800;
	public static int OriginHeight = 600;
	public static int Width = 800;
	public static int Height = 600;
	public static float magnificationX = 1f; // 倍率
	public static float magnificationY = 1f; // 倍率
	private Container contentPane;
	private JPanel jPanel;
	public int ginfo_cx=0, ginfo_cy=0;
	public int ginfo_winx = 800, ginfo_winy=600;
	public int ginfo_r, ginfo_g, ginfo_b;
	int ginfo_r_, ginfo_g_, ginfo_b_;// "_"を内部用にしてついてないのは外から変更してもスルーするようにする？
	private boolean isRedraw=true;
	Font f;
	List<JComponent> objects=new ArrayList<>();
	List<JComponent> allObjects=new ArrayList<>();		//JLabel系も含む
	int ginfo_mesx, ginfo_mesy;
	private int objWidth=100;
	private int objHeight=20;
	public int stat;

	public HSP(String title){
		setTitle(title);								// ウィンドウタイトル設定
		setBounds(50, 0, OriginWidth, OriginHeight);	// ウィンドウサイズ設定
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// ｘボタンで終了
		setLocationRelativeTo(null);
		contentPane = getContentPane();					// JFrameの画面情報取得
		jPanel = new JPanel();							// 扱いやすいように基本ほとんどのオブジェクトはjPanelに描画する
		jPanel.setLayout(null);							// 自動配置ではなく座標指定出来るようにする
		contentPane.add(jPanel, BorderLayout.CENTER);	// 表示されるように
		//add(contentPane);
		//image = getToolkit().getImage("./image.png");
		setVisible(true);
		setFocusable(true);
		//Input input=new Input();
		//this.addMouseListener(input);
		//this.addKeyListener(input);
		f=new Font("ＭＳ ゴシック", Font.BOLD, 16);
	}
	public HSP(){
		this("Title");
	}

	/*	public void changeWindowSize(int x, int y){
		Width = x;
		Height = y;
		magnificationX = 0f + Width / OriginWidth;
		magnificationY = 0f + Height / OriginHeight;
		setBounds(getX(), getY(), Width, Height);
	}*/

	/*	@Override
	public void repaint(){
		contentPane.removeAll();
		jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.add(new JLabel("dfsa"));
		contentPane.add(jPanel, BorderLayout.CENTER);
		super.revalidate();
		super.repaint();
	}
	 */
	/*
	public void paint(Graphics g){
		super.paint(g);
	}
	 */
	/// 画面描画命令
	public void mes(String s) {
		int stringHeight=f.getSize()*s.split("\n").length;// 行数

		JLabel j=new JLabel();
		j.setFont(f);
		j.setText(s.replaceAll("\n", "<br />"));
		j.setBounds(ginfo_cx, ginfo_cy, getFontMetrics(f).stringWidth(s), stringHeight);// getFontMetrics(f).stringWidth(s)
		j.setForeground(new Color(ginfo_r, ginfo_g, ginfo_b) );
		jPanel.add(j);
		redraw();

		ginfo_cy+= stringHeight;
		ginfo_mesx=getFontMetrics(f).stringWidth(s);
		ginfo_mesy=stringHeight;

	}
	private void redraw() {
		if(isRedraw) {
			//super.revalidate();
			super.repaint();
		}
	}
	public void redraw(int x) {
		if(x==0) {
			isRedraw=false;
		}else {
			isRedraw=true;
		}
	}
	/*	"fontname"   : フォント名
	p1=1～99(12) : フォントの大きさ
	p2=0～(0)    : フォントのスタイル*/
	public void font(String name, int size, int style) {
		// styleがまだ適当
		f=new Font(name, style, size);
	}
	public void font(String string, int size) {
		font(string, size, 0);
	}
	public void picload(String path) {//,x
		//Image image = getToolkit().getImage("./image.png");
		ImageIcon icon = new ImageIcon(path);
		JLabel label = new JLabel(icon);
		label.setBounds(ginfo_cx, ginfo_cy, icon.getIconWidth(), icon.getIconHeight());
		jPanel.add(label);
		ginfo_cy+=icon.getIconHeight();
		redraw();
	}
	public void pos(Integer x, Integer y) {
		if(x!=null) ginfo_cx=x;
		if(y!=null) ginfo_cy=y;
	}
	public void title(String s) {
		setTitle(s);
	}
	private JButton button_(String s) {
		JButton b = new JButton(s);
		b.setBounds(ginfo_cx, ginfo_cy, objWidth, objHeight);
		ginfo_cy+=objHeight;
		jPanel.add(b);
		objects.add(b);
		stat=objects.indexOf(b);
		return b;
	}
	public void button(String s, IntConsumer func) {
		button_(s).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.accept(objects.indexOf(e.getSource()));
			}
		});
	}
	public void button(String s, Runnable func) {
		button_(s).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				func.run();
			}
		});
	}
	public void objsize (int x, int y) {
		objWidth=x;
		objHeight=y;
	}
	public void screen(Integer id, Integer width, Integer height, Integer style, Integer x, Integer y) {
		setBounds(x, y, width, height);	// ウィンドウサイズ設定
	}
	public void screen(int i, int j, int k) {
		screen(i, j, k, 0, 0, 0);
	}
	public void color(int r, int g, int b) {
		ginfo_r=r;
		ginfo_g=g;
		ginfo_b=b;
	}
	public void color(Color c) {
		ginfo_r=c.getRed();
		ginfo_g=c.getGreen();
		ginfo_b=c.getBlue();

	}
	public void cls() {
		jPanel.removeAll();
		ginfo_cx=0;
		ginfo_cy=0;

	}
	public void await(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void wait_(int a) {
		await(a*10);
	}
	public void line(int x1, int y1, int x2, int y2) {
		MyComponent l=MyComponent.NewLine(x1, y1, x2, y2);// 線描画用インスタンス生成
		l.setBounds(jPanel.getBounds());
		l.setForeground(new Color(ginfo_r, ginfo_g, ginfo_b));
		jPanel.add(l);
		redraw();// 強制再描画
	}
}
class MyComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	private int x1, y1, x2, y2;
	enum Mode{Pset, Line, Boxf, Circle }
	public Mode mode;
	MyComponent(int x1, int y1, int x2, int y2){
		super();
		this.x1 =x1; this.y1 =y1;
		this.x2 =x2; this.y2 =y2;
	}
	public static MyComponent NewLine(int x1, int y1, int x2, int y2) {
		MyComponent mc = new MyComponent(x1, y1, x2, y2);
		mc.mode=Mode.Line;
		return mc;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch(mode) {
		case Boxf:
			break;
		case Circle:
			break;
		case Pset:
			break;
		case Line:
			g.drawLine(x1, y1, x2, y2);
			break;
		default:
			break;
		}
	}
	static int large(int a, int b) {
		return a<b? b: a;
	}
	static int small(int a, int b) {
		return a<b? a: b;
	}
}

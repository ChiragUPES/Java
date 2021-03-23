
import java.io.*;
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
public class Server  extends JFrame implements ActionListener {
	JPanel p1;// Panel is as same as div did in html.
			// Jab bhi ham frame pr kuch likte hai to vo jlabel ki help se kiha jata hai
	JTextField t1;
	JButton b1;
	static JTextArea a1;
	static ServerSocket skt;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	boolean typing;

	// All the coding for the frame are inside the constructor
	Server(){
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,60);
		add(p1);
		
		
		// for dp
		ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
		Image i5=i4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i6=new ImageIcon(i5);
		JLabel l2=new JLabel(i6);
		l2.setBounds(40, 10, 40, 30);
		p1.add(l2);
		
		// for Active Now
		JLabel l4=new JLabel("Active Now");
		l4.setFont(new Font("SAN_SERIF",Font.BOLD,12));
		l4.setForeground(Color.WHITE);
		l4.setBounds(90, 25, 100, 40);
		p1.add(l4); 
		
		Timer t=new Timer(1,new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				if(!typing ) {
					l4.setText("Active Now");
		
				}
			}
			
		});
		t.setInitialDelay(2000);
		
		
		// for name
		JLabel l3=new JLabel("Gaitonde");
		l3.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
		l3.setForeground(Color.WHITE);
		l3.setBounds(90, 13, 100, 18);
		p1.add(l3); 
		
		
		
		
		
		ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
		Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i9=new ImageIcon(i8);
		JLabel l5=new JLabel(i9);
		l5.setBounds(300, 10, 25, 50);
		p1.add(l5);
		
		ImageIcon i11=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
		Image i12=i11.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i13=new ImageIcon(i12);
		JLabel l6=new JLabel(i13);
		l6.setBounds(350, 10, 25, 50);
		p1.add(l6);
		
		
		
		ImageIcon i14=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
		Image i15=i14.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i16=new ImageIcon(i15);
		JLabel l7=new JLabel(i16);
		l7.setBounds(400, 10, 25, 50);
		p1.add(l7);
		
		
		
		
		
		
		
		
		// for back button
		// for image
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
		
		// image ka size change krne k liye
		Image i2=i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		
		
		ImageIcon i3= new ImageIcon(i2);
		
		// image icons ko label pr dalna padta hai..hm directly usko nhi daal skte
		JLabel l1=new JLabel(i3);
		l1.setBounds(5,10,30,30); // image ki location first 2 parameter mein ..or last two mein image ka size
		
		p1.add(l1); // used to add the element on frame
	// 	 getContentPane().setBackground(Color.YELLOW);
		 
	// getConetentPane()-> pure bakground ko uthaega or set background change krega
		setLayout(null);
		setSize(450,600); //frame ka size define krne k liye
		setLocation(200,150); // frame ki location
		setUndecorated(true); // minimize,maximumize wale ko htane k liye
		setVisible(true); // by defult false hota hai
		
		
		// mouse event on back button it will exit the program
		l1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.exit(0);
			}
		});
		
		// text field
		t1=new JTextField();
		t1.setBounds(5, 560, 370, 30);
		t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		add(t1);
		
		t1.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke)
			{
				l4.setText("typing..");
				t.stop();
				typing=true;
			}
			public void keyReleased(KeyEvent ke)
			{
				typing=false;
				if(!t.isRunning())
				{
					t.start();
				}
			}
		});
		
		// button
		b1=new JButton("Send");
		b1.setBounds(370, 560, 70, 28);
		b1.setBackground(new Color(7,94,84));
		b1.setForeground(Color.WHITE);
		b1.addActionListener(this);
		add(b1);
		
		//text area
		a1=new JTextArea();
		a1.setBounds(5,65,430,490);
		a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		a1.setBackground(Color.WHITE);
		a1.setEditable(false);
		a1.setLineWrap(true);
		a1.setWrapStyleWord(true);
		add(a1);
		
		
		
		

	}
	
	public static void main(String[] args)
	{
		new Server().setVisible(true);
		String msginput="";
		 try {
			 skt=new ServerSocket(6001);// passes port number
			 s=skt.accept(); // accept the data
			 din=new DataInputStream(s.getInputStream());
			 dout=new DataOutputStream(s.getOutputStream());
			 msginput=din.readUTF();
			 a1.setText(a1.getText()+"\n"+msginput);
			 
			 skt.close();
			 s.close();
		 }catch(Exception e)
		 {
			 
		 }
	}

	//this method is in the ActionListener and we have to override this method.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// text field wali value ko text area mein laya hai
		try {
			
		String out=t1.getText();
		a1.setText(a1.getText()+"\n\t\t\t"+out);
		dout.writeUTF(out);
		t1.setText("");
		}catch(Exception e)
		{
			
		}
		
	}
}

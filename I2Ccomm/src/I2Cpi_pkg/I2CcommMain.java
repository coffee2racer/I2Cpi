package I2Cpi_pkg;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;


public class I2CcommMain extends JFrame {
	private static final long serialVersionUID = 2016042900000000000L;  // unique id
	private JPanel contentPane;
	private static JCheckBox m_JCbxInput_00;
	private static JCheckBox m_JCbxInput_01;
	private static JCheckBox m_JCbxInput_02;
	private static JCheckBox m_JCbxInput_03;
	private static JCheckBox m_JCbxInput_04;
	private static JCheckBox m_JCbxInput_05;
	private static JCheckBox m_JCbxInput_06;
	private static JCheckBox m_JCbxInput_07;
	private static JCheckBox m_JCbxOutput_00;
	private static JCheckBox m_JCbxOutput_01;
	private static JCheckBox m_JCbxOutput_02;
	private static JCheckBox m_JCbxOutput_03;
	private static JCheckBox m_JCbxOutput_04;
	private static JCheckBox m_JCbxOutput_05;
	private static JCheckBox m_JCbxOutput_06;
	private static JCheckBox m_JCbxOutput_07;

	public static Class<?> m_Class;
	public static Object m_Obj;
	public static Method m_I2C_Open;
	public static Method m_I2C_Close;
	public static Method m_I2C_Read_PCF8574;
	public static Method m_I2C_Write_PCF8574;
	public static Method m_I2C_Comm;

	static byte m_byBus = 0x01;
	static byte m_byDevAddr = 0x38;
	static byte m_byRegAddr = 0x00;
	static byte[] m_byReadValue = new byte[1];
	static byte m_byWriteValue;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Load the class "I2Cpi"
			m_Class = Class.forName("I2Cpi");
	
			// Create instance/object of the class Outside.
			m_Obj = m_Class.newInstance();
	
			// Calling the method I2C_open(byte byDeviceAddr) of class I2Cpi and add parameter.
			m_I2C_Open = m_Class.getDeclaredMethod("I2C_open", new Class[]{byte.class});
	
			// Calling the method I2C_close() of class Outside.
			m_I2C_Close = m_Class.getDeclaredMethod("I2C_close");
	
			// Calling the method I2C_read_PCF8574(byte byDeviceAddr, byte RegisterAddr, byte[] byValue) of class I2Cpi and add parameter.
			m_I2C_Read_PCF8574 = m_Class.getDeclaredMethod("I2C_read_PCF8574", new Class[]{byte.class, byte.class, byte[].class});
			
			// Calling the method I2C_write_PCF8574(byte byDeviceAddr, byte byRegisterAddr, byte byValue) of class I2Cpi and add parameter.
			m_I2C_Write_PCF8574 = m_Class.getDeclaredMethod("I2C_write_PCF8574", new Class[]{byte.class, byte.class, byte.class});
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		
		if(I2C_open(m_byBus) != 0) return;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I2CcommMain frame = new I2CcommMain();
					frame.setVisible(true);

					frame.addWindowListener(new java.awt.event.WindowAdapter() {
						@Override
						public void windowClosing(java.awt.event.WindowEvent windowEvent) {								
						// if (JOptionPane.showConfirmDialog(frame, 
								// "Are you sure to close this window?", "Really Closing?", 
								// JOptionPane.YES_NO_OPTION,
								// JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
								// System.exit(0);
							// }
							I2C_close();
							//ApplicationInstanceManager.closeInstance();
							System.exit(0);
						}
					});			
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public I2CcommMain() {
		setTitle("I2C communication");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./res/TECIA.png"));

		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			String str = "\"Nimbus Look and Feel\" not found. (" + e.toString() + ")";
			JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.OK_OPTION);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeadline_07 = new JLabel("7");
		lblHeadline_07.setBounds(106, 17, 13, 14);
		contentPane.add(lblHeadline_07);
		
		JLabel lblHeadline_06 = new JLabel("6");
		lblHeadline_06.setBounds(130, 17, 13, 14);
		contentPane.add(lblHeadline_06);
		
		JLabel lblHeadline_05 = new JLabel("5");
		lblHeadline_05.setBounds(152, 17, 13, 14);
		contentPane.add(lblHeadline_05);
		
		JLabel lblHeadline_04 = new JLabel("4");
		lblHeadline_04.setBounds(176, 17, 13, 14);
		contentPane.add(lblHeadline_04);
		
		JLabel lblHeadline_03 = new JLabel("3");
		lblHeadline_03.setBounds(198, 17, 13, 14);
		contentPane.add(lblHeadline_03);
		
		JLabel lblHeadline_02 = new JLabel("2");
		lblHeadline_02.setBounds(222, 17, 13, 14);
		contentPane.add(lblHeadline_02);
		
		JLabel lblHeadline_01 = new JLabel("1");
		lblHeadline_01.setBounds(244, 17, 13, 14);
		contentPane.add(lblHeadline_01);
		
		JLabel lblHeadline_00 = new JLabel("0");
		lblHeadline_00.setBounds(268, 17, 13, 14);
		contentPane.add(lblHeadline_00);
		
		JLabel lblInputs = new JLabel("Inputs:");
		lblInputs.setBounds(29, 37, 67, 15);
		contentPane.add(lblInputs);
		
		m_JCbxInput_07 = new JCheckBox("");
		m_JCbxInput_07.setEnabled(false);
		m_JCbxInput_07.setBounds(99, 34, 21, 23);
		contentPane.add(m_JCbxInput_07);
		
		m_JCbxInput_06 = new JCheckBox("");
		m_JCbxInput_06.setEnabled(false);
		m_JCbxInput_06.setBounds(122, 34, 21, 23);
		contentPane.add(m_JCbxInput_06);
		
		m_JCbxInput_05 = new JCheckBox("");
		m_JCbxInput_05.setEnabled(false);
		m_JCbxInput_05.setBounds(145, 34, 21, 23);
		contentPane.add(m_JCbxInput_05);
		
		m_JCbxInput_04 = new JCheckBox("");
		m_JCbxInput_04.setEnabled(false);
		m_JCbxInput_04.setBounds(168, 34, 21, 23);
		contentPane.add(m_JCbxInput_04);
		
		m_JCbxInput_03 = new JCheckBox("");
		m_JCbxInput_03.setEnabled(false);
		m_JCbxInput_03.setBounds(191, 34, 21, 23);
		contentPane.add(m_JCbxInput_03);
		
		m_JCbxInput_02 = new JCheckBox("");
		m_JCbxInput_02.setEnabled(false);
		m_JCbxInput_02.setBounds(214, 34, 21, 23);
		contentPane.add(m_JCbxInput_02);
		
		m_JCbxInput_01 = new JCheckBox("");
		m_JCbxInput_01.setEnabled(false);
		m_JCbxInput_01.setBounds(237, 34, 21, 23);
		contentPane.add(m_JCbxInput_01);
		
		m_JCbxInput_00 = new JCheckBox("");
		m_JCbxInput_00.setEnabled(false);
		m_JCbxInput_00.setBounds(260, 34, 21, 23);
		contentPane.add(m_JCbxInput_00);

		JLabel lblOutputs = new JLabel("Outputs:");
		lblOutputs.setBounds(29, 67, 67, 15);
		contentPane.add(lblOutputs);
		
		m_JCbxOutput_07 = new JCheckBox("");
		m_JCbxOutput_07.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(m_JCbxOutput_07.isSelected()) m_byWriteValue = (byte)SetBit(m_byWriteValue, 7);
				else                             m_byWriteValue = (byte)ClrBit(m_byWriteValue, 7);
				I2C_write_PCF8574(m_byDevAddr, m_byRegAddr, (byte)(m_byWriteValue ^ 0xFF));
			}
		});
		m_JCbxOutput_07.setBounds(99, 64, 21, 23);
		contentPane.add(m_JCbxOutput_07);
		
		m_JCbxOutput_06 = new JCheckBox("");
		m_JCbxOutput_06.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(m_JCbxOutput_06.isSelected()) m_byWriteValue = (byte)SetBit(m_byWriteValue, 6);
				else                             m_byWriteValue = (byte)ClrBit(m_byWriteValue, 6);
				I2C_write_PCF8574(m_byDevAddr, m_byRegAddr, (byte)(m_byWriteValue ^ 0xFF));
			}
		});
		m_JCbxOutput_06.setBounds(122, 64, 21, 23);
		contentPane.add(m_JCbxOutput_06);
		
		m_JCbxOutput_05 = new JCheckBox("");
		m_JCbxOutput_05.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(m_JCbxOutput_05.isSelected()) m_byWriteValue = (byte)SetBit(m_byWriteValue, 5);
				else                             m_byWriteValue = (byte)ClrBit(m_byWriteValue, 5);
				I2C_write_PCF8574(m_byDevAddr, m_byRegAddr, (byte)(m_byWriteValue ^ 0xFF));
			}
		});
		m_JCbxOutput_05.setBounds(145, 64, 21, 23);
		contentPane.add(m_JCbxOutput_05);
		
		m_JCbxOutput_04 = new JCheckBox("");
		m_JCbxOutput_04.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(m_JCbxOutput_04.isSelected()) m_byWriteValue = (byte)SetBit(m_byWriteValue, 4);
				else                             m_byWriteValue = (byte)ClrBit(m_byWriteValue, 4);
				I2C_write_PCF8574(m_byDevAddr, m_byRegAddr, (byte)(m_byWriteValue ^ 0xFF));
			}
		});
		m_JCbxOutput_04.setBounds(168, 64, 21, 23);
		contentPane.add(m_JCbxOutput_04);
		
		m_JCbxOutput_03 = new JCheckBox("");
		m_JCbxOutput_03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(m_JCbxOutput_03.isSelected()) m_byWriteValue = (byte)SetBit(m_byWriteValue, 3);
				else                             m_byWriteValue = (byte)ClrBit(m_byWriteValue, 3);
				I2C_write_PCF8574(m_byDevAddr, m_byRegAddr, (byte)(m_byWriteValue ^ 0xFF));
			}
		});
		m_JCbxOutput_03.setBounds(191, 64, 21, 23);
		contentPane.add(m_JCbxOutput_03);
		
		m_JCbxOutput_02 = new JCheckBox("");
		m_JCbxOutput_02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(m_JCbxOutput_02.isSelected()) m_byWriteValue = (byte)SetBit(m_byWriteValue, 2);
				else                             m_byWriteValue = (byte)ClrBit(m_byWriteValue, 2);
				I2C_write_PCF8574(m_byDevAddr, m_byRegAddr, (byte)(m_byWriteValue ^ 0xFF));
			}
		});
		m_JCbxOutput_02.setBounds(214, 64, 21, 23);
		contentPane.add(m_JCbxOutput_02);
		
		m_JCbxOutput_01 = new JCheckBox("");
		m_JCbxOutput_01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(m_JCbxOutput_01.isSelected()) m_byWriteValue = (byte)SetBit(m_byWriteValue, 1);
				else                             m_byWriteValue = (byte)ClrBit(m_byWriteValue, 1);
				I2C_write_PCF8574(m_byDevAddr, m_byRegAddr, (byte)(m_byWriteValue ^ 0xFF));
			}
		});
		m_JCbxOutput_01.setBounds(237, 64, 21, 23);
		contentPane.add(m_JCbxOutput_01);
		
		m_JCbxOutput_00 = new JCheckBox("");
		m_JCbxOutput_00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(m_JCbxOutput_00.isSelected()) m_byWriteValue = (byte)SetBit(m_byWriteValue, 0);
				else                             m_byWriteValue = (byte)ClrBit(m_byWriteValue, 0);
				I2C_write_PCF8574(m_byDevAddr, m_byRegAddr, (byte)(m_byWriteValue ^ 0xFF));
			}
		});
		m_JCbxOutput_00.setBounds(260, 64, 21, 23);
		contentPane.add(m_JCbxOutput_00);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				I2C_close();
				//ApplicationInstanceManager.closeInstance();
				System.exit(0);
			}
		});
		btnExit.setBounds(190, 110, 90, 26);
		contentPane.add(btnExit);

		TimerTask task = new TimerTask()
		{
			@Override
			public void run()
			{
				int iRtc = I2C_read_PCF8574(m_byDevAddr, m_byRegAddr, m_byReadValue);
				
				if(iRtc == 0)
				{
					if(TestBit(m_byReadValue[0], 0) == 1) m_JCbxInput_00.setSelected(true);
					else                                  m_JCbxInput_00.setSelected(false);
					
					if(TestBit(m_byReadValue[0], 1) == 1) m_JCbxInput_01.setSelected(true);
					else                                  m_JCbxInput_01.setSelected(false);
					
					if(TestBit(m_byReadValue[0], 2) == 1) m_JCbxInput_02.setSelected(true);
					else                                  m_JCbxInput_02.setSelected(false);
					
					if(TestBit(m_byReadValue[0], 3) == 1) m_JCbxInput_03.setSelected(true);
					else                                  m_JCbxInput_03.setSelected(false);
					
					if(TestBit(m_byReadValue[0], 4) == 1) m_JCbxInput_04.setSelected(true);
					else                                  m_JCbxInput_04.setSelected(false);
					
					if(TestBit(m_byReadValue[0], 5) == 1) m_JCbxInput_05.setSelected(true);
					else                                  m_JCbxInput_05.setSelected(false);
					
					if(TestBit(m_byReadValue[0], 6) == 1) m_JCbxInput_06.setSelected(true);
					else                                  m_JCbxInput_06.setSelected(false);
					
					if(TestBit(m_byReadValue[0], 7) == 1) m_JCbxInput_07.setSelected(true);
					else                                  m_JCbxInput_07.setSelected(false);
				}
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 0, 100);
	}

	public static int I2C_open(byte byI2C_Bus)
	{
		int iRtc = -99;

		try {
			iRtc = (int)m_I2C_Open.invoke(m_Obj, byI2C_Bus);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			String str = "Opening of I2C bus failed. (" + e.toString() + ")";
			JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.OK_OPTION);
		}
		
		return iRtc;
	}

	public static int I2C_close()
	{
		int iRtc = -99;

		try {
			iRtc = (int)m_I2C_Close.invoke(m_Obj);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			String str = "Closing of I2C bus failed. (" + e.toString() + ")";
			JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.OK_OPTION);
		}
		
		return iRtc;
	}

	public static int I2C_read_PCF8574(byte byDeviceAddr, byte byRegisterAddr, byte[] byValue)
	{
		int iRtc = -99;

		try {
			iRtc = (int)m_I2C_Read_PCF8574.invoke(m_Obj, byDeviceAddr, byRegisterAddr, byValue);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			String str = "Reading from I2C bus failed. (" + e.toString() + ")";
			JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.OK_OPTION);
		}
		
		return iRtc;
	}

	public static int I2C_write_PCF8574(byte byDeviceAddr, byte byRegisterAddr, byte byValue)
	{
		int iRtc = -99;

		try {
			iRtc = (int)m_I2C_Write_PCF8574.invoke(m_Obj, byDeviceAddr, byRegisterAddr, byValue);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			String str = "Writing to I2C bus failed. (" + e.toString() + ")";
			JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.OK_OPTION);
		}
		
		return iRtc;
	}

	static int TestBit (int iVar, int iX) { return((iVar >> iX) & 1); }
	static int SetBit  (int iVar, int iX) { return(iVar |= (1 << iX)); }
	static int ClrBit  (int iVar, int iX) { return(iVar &= (~(1 << iX))); }
	static int ChgBit  (int iVar, int iX) { return(iVar ^= (1 << iX)); }
	static int HighByte(int iVar)         { return ((iVar &= 0xFF00) >> 8); }
	static int LowByte (int iVar)         { return (iVar &= 0x00FF); }
}

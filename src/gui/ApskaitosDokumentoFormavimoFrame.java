package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import atributai.Atributai;
import atributai.TiekejuAtributai;
import funkcionalumas.DuomenuBaze.DBduomenys;
import funkcionalumas.DuomenuBaze.SQLuzklausos;
import kitosKlases.DateLabelFormatter;
import layout.SpringUtilities;

public class ApskaitosDokumentoFormavimoFrame extends JFrame{
	
	private Dimension EkranoDydis = Toolkit.getDefaultToolkit().getScreenSize();
	
	private double plotis = EkranoDydis.getWidth();
	
	private double aukstis = EkranoDydis.getHeight();	
	
	
	private JPanel ivedimoLaukuPan;
	private JPanel duomenuPanele;
	protected JPanel mygtukuPanele;
	private JPanel prekiuMygtukuPanele;
	
	private JPanel card1;
	private JPanel card2;	
	
	private JPanel leftForm;
	private JPanel rightForm;
	
	private JPanel imoniuDuomenuPanele;
	
	private JLabel serijaLbl;
	private JLabel numerisLbl;
	private JLabel dataLbl;
	private JLabel apmoketiIkiLbl;
	
	protected JTextField serijaTxtFld;
	protected JTextField numerisTxtFld;

	protected UtilDateModel dataModel;
	protected UtilDateModel apmoketiIkiModel;
	
	private JDatePickerImpl dataDatePicker;
	private JDatePickerImpl apmoketiIkiDatePicker;
	
	private JLabel imonesKodasLbl;
	private JLabel pavadinimasLbl;
	private JLabel pvmKodasLbl;
	private JLabel adresasLbl;

	protected JTextField imonesKodasFld;
	protected JTextField pavadinimasFld;
	protected JTextField pvmKodasFld;
	protected JTextField adresasFld;
	
	protected JButton pasirinktiImone;
	protected JButton pasirinktiPreke;
	protected JButton redaguotiPasirinktaPreke;
//	protected JButton pajamuoti;
	
	JTabbedPane tabbedPane;

	protected DefaultTableModel modelisPasirinktomsPrekems;

	private JTable lentelePasirinktomsPrekems;

	private JScrollPane scrollpanePasirinktomsPrekems;
	
	protected JCheckBox apmoketaCheckBox;

	protected Statement st;
	
	protected ApskaitosDokumentoFormavimoFrame adff = this;	
	
	protected IrasuPerziurosPanele ipp;
	
	protected String tab1Caption,
					 tab2Caption;
	
	protected PrekesIvedimoForma pif;
	
	protected PrekesIvedimoFormaSaskaitosIsrasymui pifsi;
	
	public JTextField getImonesKodasFld() {
		return imonesKodasFld;
	}

	public JTextField getPavadinimasFld() {
		return pavadinimasFld;
	}

	public JTextField getPvmKodasFld() {
		return pvmKodasFld;
	}

	public JTextField getAdresasFld() {
		return adresasFld;
	}

	public JTable getLentelePasirinktomsPrekems() {
		return lentelePasirinktomsPrekems;
	}

	public DefaultTableModel getModelisPasirinktomsPrekems() {
		return modelisPasirinktomsPrekems;
	}

	public ApskaitosDokumentoFormavimoFrame(Statement st, IrasuPerziurosPanele ipp) {
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setTitle("Pirkimø - pardavimø apskaita");
		setSize((int)plotis/2, (int)aukstis/2);
		setResizable(true);
		
		this.st = st;
		this.ipp = ipp;
		
		init();
		addActionListeners();
		render();
		
		setVisible(true);		
		
	}

	protected void addActionListeners() {

		
		serijaTxtFld.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
      //      	serijaTxtFld.setText(dataModel.getYear()+"-"+(dataModel.getMonth()+1)+"-"+dataModel.getDay());
            }
        });
		
	}

	protected void render() {

		setLayout(new BorderLayout(10, 10));
				
				
		leftForm.add(serijaLbl);
		leftForm.add(serijaTxtFld);
		leftForm.add(numerisLbl);
		leftForm.add(numerisTxtFld);
		rightForm.add(dataLbl);
		rightForm.add(dataDatePicker);
		rightForm.add(apmoketiIkiLbl);
		rightForm.add(apmoketiIkiDatePicker);
		
        SpringUtilities.makeCompactGrid(leftForm,
                                        2, 2,  //rows, cols
                                        3, 3,  //initX, initY
                                        3, 3); //xPad, yPad
 
        SpringUtilities.makeCompactGrid(rightForm,
						                2, 2,  //rows, cols
						                3, 3,  //initX, initY
						                3, 3); //xPad, yPad
        

        
		ivedimoLaukuPan.add(leftForm);
		ivedimoLaukuPan.add(rightForm);
		
        imoniuDuomenuPanele.add(imonesKodasLbl);
        imoniuDuomenuPanele.add(imonesKodasFld);
        imoniuDuomenuPanele.add(pavadinimasLbl);
        imoniuDuomenuPanele.add(pavadinimasFld);
        imoniuDuomenuPanele.add(pvmKodasLbl);
        imoniuDuomenuPanele.add(pvmKodasFld);
        imoniuDuomenuPanele.add(adresasLbl);
        imoniuDuomenuPanele.add(adresasFld);	
        
        SpringUtilities.makeCompactGrid(imoniuDuomenuPanele,
						                4, 2,  //rows, cols
						                3, 3,  //initX, initY
						                3, 3); //xPad, yPad		

        JPanel panel = new JPanel();
        panel.add(pasirinktiImone);
        
		
		card1.add(imoniuDuomenuPanele, BorderLayout.PAGE_START);
		card1.add(panel, BorderLayout.CENTER);
		
//		modelisPasirinktomsPrekems.setColumnIdentifiers(new String[]{
//				"Prekes kodas",
//				"Bar kodas",
//				"Pavadinimas",
//				"Matavimo vienetas",
//				"Pirkimo kaina",
//				"Antkainio procentas",
//				"Kiekis", 
//				"PVM"
//			});
		
		card2.add(scrollpanePasirinktomsPrekems, BorderLayout.CENTER);
		
		prekiuMygtukuPanele.add(pasirinktiPreke);
		prekiuMygtukuPanele.add(redaguotiPasirinktaPreke);	
		
		card2.add(prekiuMygtukuPanele, BorderLayout.PAGE_END);
		
		tabbedPane.addTab(tab1Caption, card1);
		tabbedPane.addTab(tab2Caption, card2);
		
		duomenuPanele.add(tabbedPane, BorderLayout.CENTER);
		
		mygtukuPanele.add(apmoketaCheckBox, BorderLayout.LINE_START);
//		mygtukuPanele.add(pajamuoti, BorderLayout.LINE_END);
		
		add(ivedimoLaukuPan, BorderLayout.PAGE_START);
		add(duomenuPanele, BorderLayout.CENTER);
		add(mygtukuPanele, BorderLayout.PAGE_END);
	}

	protected void init() {

		ivedimoLaukuPan = new JPanel(new GridLayout(1, 2));
		duomenuPanele = new JPanel(new BorderLayout());
		mygtukuPanele = new JPanel(new BorderLayout());	
		prekiuMygtukuPanele = new JPanel(new GridLayout(1, 2));
		
		serijaLbl = new JLabel("Serija:", JLabel.TRAILING);
		numerisLbl = new JLabel("Numeris:", JLabel.TRAILING);
		dataLbl = new JLabel("Data:", JLabel.TRAILING);
		apmoketiIkiLbl = new JLabel("Apmokëti iki:", JLabel.TRAILING);
		
		serijaTxtFld = new JTextField();
		numerisTxtFld = new JTextField();
				
		leftForm = new JPanel(new SpringLayout());
		rightForm = new JPanel(new SpringLayout());		
					
		tab1Caption = "TAB 1 caption";
		tab2Caption = "TAB 2 caption";
		
		card1 = new JPanel(new BorderLayout());
		card2 = new JPanel(new BorderLayout());
		
		imoniuDuomenuPanele = new JPanel(new SpringLayout());
		
		imonesKodasLbl = new JLabel("Ámonës kodas:", JLabel.TRAILING);
		pavadinimasLbl = new JLabel("Pavadinimas:", JLabel.TRAILING);
		pvmKodasLbl = new JLabel("PVM kodas:", JLabel.TRAILING);
		adresasLbl = new JLabel("Adresas:", JLabel.TRAILING);

		imonesKodasFld = new JTextField();
		pavadinimasFld = new JTextField();
		pvmKodasFld = new JTextField();
		adresasFld = new JTextField();
		
		pasirinktiImone = new JButton("Pasirinkti ámonæ ið sàraðo");
		
		tabbedPane = new JTabbedPane();
		
//		pajamuoti = new JButton("Pajamuoti");
		
		
		dataModel = new UtilDateModel();
		apmoketiIkiModel = new UtilDateModel();

		Properties p = new Properties();
		p.put("text.today", "Ðiandien");
		p.put("text.month", "Mënuo");
		p.put("text.year", "Metai");
		JDatePanelImpl datePanel = new JDatePanelImpl(dataModel, p);
		JDatePanelImpl datePanel1 = new JDatePanelImpl(apmoketiIkiModel, p);

		dataDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		apmoketiIkiDatePicker = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		
		modelisPasirinktomsPrekems = new DefaultTableModel() {

			public boolean isCellEditable(int row, int column) {

		        return false;
		        
		    }
		    
		};
		
		lentelePasirinktomsPrekems = new JTable(modelisPasirinktomsPrekems);
		
		lentelePasirinktomsPrekems.getTableHeader().setReorderingAllowed(false);
		
		scrollpanePasirinktomsPrekems = new JScrollPane(lentelePasirinktomsPrekems);
		
		pasirinktiPreke = new JButton("Ávesti prekës duomenis");
		redaguotiPasirinktaPreke = new JButton("Redaguoti prekës duomenis");
		
		apmoketaCheckBox = new JCheckBox("Apmokëta");
		
		

		
	}

}

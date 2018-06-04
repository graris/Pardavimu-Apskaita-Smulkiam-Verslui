package kitosKlases;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import net.sf.dynamicreports.examples.Templates;

//import javax.xml.transform.Templates;

import net.sf.dynamicreports.examples.complex.invoice.Customer;
import net.sf.dynamicreports.examples.complex.invoice.InvoiceData;
import net.sf.dynamicreports.examples.complex.invoice.Item;
//import net.sf.jasperreports.engine.export.oasis.StyleBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;


public class InvoiceDesign {
	private InvoiceData data = new InvoiceData();
	private AggregationSubtotalBuilder<BigDecimal> totalSum;
	private String  serija,
	                invoicingDate,
	                susimoketiIkiData;

	public void provideDataToInvoice(String invoicingDate, int invoiceId, Customer billTo, String serija, Customer shipTo, List<Item> items, String susimoketiIkiData) {
		
		this.serija = serija;
		
		this.invoicingDate = invoicingDate;
		
		this.susimoketiIkiData = susimoketiIkiData;
		
		data.getInvoice().setId(invoiceId);
		data.getInvoice().setBillTo(billTo);
		data.getInvoice().setShipTo(shipTo);
		data.getInvoice().setItems(items);
		
	}
	
	public JasperReportBuilder build() throws DRException {
		
		JasperReportBuilder report = report();
		
		StyleBuilder boldStyle = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		
		StyleBuilder titleStyle = stl.style(boldCenteredStyle)
				.setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
				.setFontSize(15);
		
		StyleBuilder bendraSumaStyle = stl.style(boldCenteredStyle)
				.setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
				.setFontSize(12);
		
		StyleBuilder serijaNumerisStyle = stl.style(boldCenteredStyle)
				.setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
				.setFontSize(10);
		
		// init styles
		StyleBuilder columnStyle = stl.style(Templates.columnStyle)
				.setBorder(stl.pen1Point());
		StyleBuilder subtotalStyle = stl.style(columnStyle)
				.bold();
		StyleBuilder shippingStyle = stl.style(Templates.boldStyle)
				.setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);

		// init columns
		TextColumnBuilder<Integer> rowNumberColumn = col.reportRowNumberColumn()
				.setFixedColumns(2)
				.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		TextColumnBuilder<String> descriptionColumn = col.column("Pavadinimas", "description", type.stringType())
				.setFixedWidth(250);
		TextColumnBuilder<Integer> quantityColumn = col.column("Kiekis", "quantity", type.integerType())
				.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Vieneto kaina", "unitprice", type.bigDecimalType());//Templates.currencyType);
		TextColumnBuilder<String> taxColumn = col.column("PVM", exp.text("20%"))
				.setFixedColumns(3);
		// price = unitPrice * quantity
		TextColumnBuilder<BigDecimal> priceColumn = unitPriceColumn.multiply(quantityColumn)
				.setTitle("Kaina")
				.setDataType(type.bigDecimalType());
		// vat = price * tax
		TextColumnBuilder<BigDecimal> vatColumn = priceColumn.multiply(data.getInvoice().getTax())
				.setTitle("Kaina su PVM")
				.setDataType(type.bigDecimalType());
		// total = price + vat
		TextColumnBuilder<BigDecimal> totalColumn = priceColumn.add(vatColumn)
				.setTitle("Suma")
				.setDataType(type.bigDecimalType())
				.setRows(2)
				.setStyle(subtotalStyle);
		// init subtotals
		totalSum = sbt.sum(totalColumn)
				.setLabel("Ið viso:")
				.setLabelStyle(Templates.boldStyle);

		// configure report
		report
				.setTemplate(Templates.reportTemplate)
				.setColumnStyle(columnStyle)
				.setSubtotalStyle(subtotalStyle)
				// columns
				.columns(
						rowNumberColumn, descriptionColumn, quantityColumn, unitPriceColumn, totalColumn, priceColumn, taxColumn, vatColumn)
				.columnGrid(
						rowNumberColumn, descriptionColumn, quantityColumn, unitPriceColumn,
						grid.horizontalColumnGridList()
								.add(totalColumn).newRow()
								.add(priceColumn, taxColumn, vatColumn))
				// subtotals
				.subtotalsAtSummary(
						totalSum, sbt.sum(priceColumn), sbt.sum(vatColumn))
				// band components
				.title(
						//cmp.image(Templates.class.getResource("Icon.jpg")).setFixedDimension(80, 80),
						cmp.text("PVM SÀSKAITA FAKTÛRA").setStyle(titleStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
						
						cmp.text("Serija: " + serija + "   Nr.:" + data.getInvoice().getId()).setStyle(serijaNumerisStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
						//Templates.createTitleComponent("Nr.: " + data.getInvoice().getId()),
						cmp.text(invoicingDate).setStyle(serijaNumerisStyle).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT),
						
						cmp.horizontalList().setStyle(stl.style(10)).setGap(50).add(
								cmp.hListCell(createCustomerComponent("Pardavëjas:", data.getInvoice().getBillTo())).heightFixedOnTop(),
								cmp.hListCell(createCustomerComponent("Pirkëjas", data.getInvoice().getShipTo())).heightFixedOnTop()),
						cmp.verticalGap(4),
						cmp.text("Apmokëti iki: " + susimoketiIkiData).setStyle(stl.style(10)).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT),
						cmp.verticalGap(2))
				.pageFooter(
						Templates.footerComponent)
				.summary(
						//cmp.text(data.getInvoice().getShipping()).setValueFormatter(Templates.createCurrencyValueFormatter("Shipping:")).setStyle(shippingStyle),
						cmp.horizontalList(
							//	cmp.text("").setStyle(Templates.bold12CenteredStyle),
								cmp.text(new TotalPaymentExpression()).setStyle(bendraSumaStyle).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT)),
						cmp.text("Kasos aparato Nr. 123 Èekio Nr. _________________________").setStyle(stl.style(10)).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
						cmp.verticalGap(50),
						cmp.text("Sàskaità iðraðë:                                                Prekes gavo:").setStyle(stl.style(10)).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
						cmp.verticalGap(20),
						cmp.text("Dëkui, kad perkate!").setStyle(Templates.bold12CenteredStyle))
				.setDataSource(data.createDataSource());

		return report;
	}

	private ComponentBuilder<?, ?> createCustomerComponent(String label, Customer customer) {
		HorizontalListBuilder list = cmp.horizontalList().setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));
		addCustomerAttribute(list, "Pavadinimas", customer.getName());
		addCustomerAttribute(list, "Adresas", customer.getAddress());
		addCustomerAttribute(list, "Ámonës kodas", customer.getCity());
		addCustomerAttribute(list, "PVM kodas", customer.getEmail());
		return cmp.verticalList(
				cmp.text(label).setStyle(Templates.boldStyle),
				list);
	}

	private void addCustomerAttribute(HorizontalListBuilder list, String label, String value) {
		if (value != null) {
			list.add(cmp.text(label + ":").setFixedColumns(8).setStyle(Templates.boldStyle), cmp.text(value)).newRow();
		}
	}

	private class TotalPaymentExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			BigDecimal total = reportParameters.getValue(totalSum);
			BigDecimal shipping = total.add(new BigDecimal(0));
			return "Mokëjimo suma: " + total.setScale(2, BigDecimal.ROUND_HALF_UP); //Templates.currencyType.valueToString(shipping, reportParameters.getLocale());
		}
	}
}
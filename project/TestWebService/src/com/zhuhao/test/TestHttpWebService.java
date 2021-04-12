package com.zhuhao.test;


import java.util.Map;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;

import com.zhuhao.util.HttpRequestUtil;
import org.omg.CORBA.TypeCode;

public class TestHttpWebService {

	
	public static void main(String[] args) {
		String xmlStr=makeXml("",null);
		String username="OD001";
		String password="";
		String url="http://60.190.141.154:8089/hxr2019/Module/InterfaceWeb/WebCodeList.asmx?op=GetCSSOrderInfo";
		try {
			HttpRequestUtil.postXMLAuthWithHttpPost(url, xmlStr, username, password);
			HttpRequestUtil.postXMLAuthWithUrlConn(url, xmlStr, username, password);
		} catch (Exception e) {
			System.out.println("异常"+e);
		}
	}
	
	/**
	 * 生成请求xml数据
	 * @param methodName 方法名 本例为"ummWaitMessageAdd"
	 * @param params 数据 (key为wsdl文件中参数的name值注意大小写和顺序都要保持一致,value为实际值)
	 * @return
	 */
	private static String makeXml(String methodName, Map<String,String> params) {
	    StringBuffer sb = new StringBuffer();
		sb.append(""
				+ "<string xmlns=\"http://tempuri.org/\">" +
//				"    <soap-env:Header/>" +
				"    <ReturnXml>" +
				"        <Code>1</Code>" +
				"        <Data>" +
				"        <OrderInfo>" +
				"            <订单号ID>40481112</订单号ID>" +
				"            <订单号>OC-2103310247</订单号>" +
				"            <客户姓名>吴鹏</客户姓名>" +
				"            <电话>15008339217</电话>" +
				"            <地址>四川省什邡市凯信装饰建材城火星人集成灶</地址>" +
				"            <订单填写日期>2021/4/1 0:00:00</订单填写日期>" +
				"            <订单类型>01</订单类型>" +
				"            <销售类型>01</销售类型>" +
				"            <发货类型>01</发货类型>" +
				"            <折后金额>41426.00</折后金额>" +
				"        </OrderInfo>" +
				"        </Data>" +
				"    </ReturnXml>" +
				"</string>");
	    System.out.println(sb);
	    return sb.toString();
	}
	
	
	/*public static String getSOPAXML() {
		Namespace soapenv = org.jdom.Namespace.getNamespace("soapenv", "http://www.w3.org/20103/05/soap-envelope");
		Namespace glob = Namespace.getNamespace("glob", "http://sap.com/xi/SAPGlobal20/Global");
		Namespace a3k = Namespace.getNamespace("a3k", "http://sap.com/xi/AP/CustomerExtension/BYD/A3KKT");

		Element Envelope = new Element("Envelope");
		Envelope.setNamespace(soapenv);
		Envelope.addNamespaceDeclaration(glob);
		Envelope.addNamespaceDeclaration(a3k);

		Element Header = new Element("Header");
		Header.setNamespace(soapenv);
		Element Body = new Element("Body");
		Body.setNamespace(soapenv);

		Element AccountingEntryBundleReplicateRequest = new Element("AccountingEntryBundleReplicateRequest");
		AccountingEntryBundleReplicateRequest.setNamespace(glob);

		Element MessageHeader = new Element("MessageHeader");
		Element ID = new Element("ID").setText("OA");
		Element CreationDateTime = new Element("CreationDateTime").setText("2018-09-17T01:00:00Z");
		Element SenderBusinessSystemID = new Element("SenderBusinessSystemID")
				.setText("_LOCAL_SYSTEM_ALIAS_SAP_INTERNAL_CONSTANT_VALUE_");
		Element RecipientBusinessSystemID = new Element("RecipientBusinessSystemID")
				.setText("_LOCAL_SYSTEM_ALIAS_SAP_INTERNAL_CONSTANT_VALUE_");
		Element BusinessScope = new Element("BusinessScope");

		Element TypeCode = new Element("TypeCode").setText("3");
		Element ID1 = new Element("ID").setText("264");
		BusinessScope.addContent(org.omg.CORBA.TypeCode).addContent(ID1);
		MessageHeader.addContent(com.sun.xml.internal.bind.v2.model.core.ID).addContent(CreationDateTime).addContent(SenderBusinessSystemID)
				.addContent(RecipientBusinessSystemID).addContent(BusinessScope);

		Element AccountingEntry = new Element("AccountingEntry");
		Element ExternalID = new Element("ExternalID").setText("OA0199");
		Element CompanyID = new Element("CompanyID").setText("2000");
		Element Note = new Element("Note").setAttribute("languageCode", "ZH").setText("抬头备注001");
		Element AccountingDocumentTypeCode = new Element("AccountingDocumentTypeCode").setText("00047");
		Element EntryDate = new Element("EntryDate").setText("2018-09-17");
		Element PostingDate = new Element("PostingDate").setText("2018-09-17");
		Element AccountingClosingStepCode = new Element("AccountingClosingStepCode").setText("10");
		Element BusinessTransactionTypeCode = new Element("BusinessTransactionTypeCode").setText("601");
		Element TransactionCurrencyCode = new Element("TransactionCurrencyCode").setText("CNY");
		Element DraftIndicator = new Element("DraftIndicator").setText("true");

		Element Item1 = new Element("Item");
		Element DebitCreditCode1 = new Element("DebitCreditCode").setText("2");
		Element ChartOfAccountsItemCode1 = new Element("ChartOfAccountsItemCode").setText("10020101");
		Element Note1 = new Element("Note").setAttribute("languageCode", "ZH").setText("银行付款");
		Element TransactionCurrencyAmount1 = new Element("TransactionCurrencyAmount")
				.setAttribute("currencyCode", "CNY").setText("10");
		Element LocalCurrencyAmount1 = new Element("LocalCurrencyAmount").setAttribute("currencyCode", "CNY")
				.setText("10");
		Item1.addContent(DebitCreditCode1).addContent(ChartOfAccountsItemCode1).addContent(Note1)
				.addContent(TransactionCurrencyAmount1).addContent(LocalCurrencyAmount1);

		Element Item2 = new Element("Item");
		Element DebitCreditCode2 = new Element("DebitCreditCode").setText("1");
		Element ChartOfAccountsItemCode2 = new Element("ChartOfAccountsItemCode").setText("65031901");
		Element CostCentreID2 = new Element("CostCentreID").setText("M2100");
		Element OverheadCostsLedgerAccountItem2 = new Element("OverheadCostsLedgerAccountItem")
				.addContent(CostCentreID2);
		Element Note2 = new Element("Note").setAttribute("languageCode", "ZH").setText("费用报销");
		Element TransactionCurrencyAmount2 = new Element("TransactionCurrencyAmount")
				.setAttribute("currencyCode", "CNY").setText("10");
		Element LocalCurrencyAmount2 = new Element("LocalCurrencyAmount").setAttribute("currencyCode", "CNY")
				.setText("10");
		Element Ex_FeeName = new Element("Ex_FeeName").setNamespace(a3k).setText("费用名称test");
		Element Ex_ProjectName = new Element("Ex_ProjectName").setNamespace(a3k).setText("项目名称test");
		Element Ex_EmployeeName = new Element("Ex_EmployeeName").setNamespace(a3k).setText("员工名称test");

		Item2.addContent(DebitCreditCode2).addContent(ChartOfAccountsItemCode2)
				.addContent(OverheadCostsLedgerAccountItem2).addContent(Note2).addContent(TransactionCurrencyAmount2)
				.addContent(LocalCurrencyAmount2).addContent(Ex_FeeName).addContent(Ex_ProjectName)
				.addContent(Ex_EmployeeName);

		AccountingEntry.addContent(ExternalID).addContent(CompanyID).addContent(Note)
				.addContent(AccountingDocumentTypeCode).addContent(EntryDate).addContent(PostingDate)
				.addContent(AccountingClosingStepCode).addContent(BusinessTransactionTypeCode)
				.addContent(TransactionCurrencyCode).addContent(DraftIndicator).addContent(Item1).addContent(Item2);

		AccountingEntryBundleReplicateRequest.addContent(MessageHeader).addContent(AccountingEntry);

		Body.addContent(AccountingEntryBundleReplicateRequest);

		Envelope.addContent(Header).addContent(Body);

		Document soapDoc = new Document(Envelope);
		XMLOutputter xmloutput = new XMLOutputter();
		String xml = xmloutput.outputString(soapDoc).replaceAll("&amp;","&");
		return xml;
	}*/
	
	
}


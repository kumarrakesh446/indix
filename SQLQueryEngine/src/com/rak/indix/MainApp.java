package com.rak.indix;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.rak.indi.models.Parameter;
import com.rak.indi.models.ParameterType;
import com.rak.indi.models.QueryModel;
import com.rak.indix.dao.QueryEngineExecuter;
import com.rak.indix.dao.QueryResult;
import com.rak.indix.utils.ScannerUtil;
import com.rak.indix.utils.consoletable.ColumnHeader;
import com.rak.indix.utils.consoletable.PrintTable;
import com.rak.indix.utils.xml.XMLProceesingException;
import com.rak.indix.utils.xml.XMLProcessing;
import com.rak.indix.utils.xml.XMLResultSet;



public class MainApp {
public static ScannerUtil scanner=new ScannerUtil();
private static PrintTable<QueryModel> table;
	public static void main(String[] args) {
		System.out.println("************************************");
		System.out.println("*Welocome To Indix SQL Query Engine*");
		System.out.println("************************************");
		String answer="";
		do {
			try {
				printSqlMenu();
				System.out.println("Please Select a Query to Execute:");
				int choice=scanner.getRangeInt(1,table.getRowLength());
				
				QueryModel query=table.getRowObectValue(choice-2, 0);
				
				System.out.println(query.getQuery());
				
				
				getQueryInputParam(query);
				QueryResult queryResult=QueryEngineExecuter.executeQuery(query);
				printQueryResult(queryResult);
				System.out.println("Do You want to continue.Y/N");
			} catch (XMLProceesingException e) {
				
				System.out.println("ERROR in Loading Menu");
			}
			
			
			 answer=scanner.getNext();
		} while ("Y".equalsIgnoreCase(answer));
	}
	private static void printQueryResult(QueryResult queryResult) {
		
		PrintTable<String> printTable=new PrintTable<>();
		
		//adding table header
		
		for (int i = 0; i < queryResult.getColumnList().size(); i++) {
			
			printTable.addColumnHeader(new ColumnHeader(ColumnHeader.COLUMN_SIZE_FULL_VIEW, queryResult.getColumnList().get(i)));
		}
		
		//adding rowss
		for (int i = 0; i < queryResult.getQueryResultList().size(); i++) {
			printTable.addNewRow(queryResult.getQueryResultList().get(i));
			//printTable.addNewRow();
		}
		printTable.print(true); 
	}
	private static void getQueryInputParam(QueryModel query) {
		
		for (Iterator<Parameter> iterator = query.getParameters().iterator(); iterator.hasNext();) {
			Parameter param =  iterator.next();
			System.out.println("Please enter parameter value("+param.getParameterType().name()+") for "+param.getName()+":");
			switch (param.getParameterType()) {
			case NUMBER:
				
				param.setValue(scanner.getNextInt()+"");
				
				break;

			case BOOLEAN:
				
				param.setValue(new Boolean(scanner.getNextBoolean()).toString().toUpperCase());
				break;
			case STRING:
				param.setValue(scanner.getNextString());
				break;
			default:
				break;
			}
			
		}
		
	}

	private static void printSqlMenu() throws XMLProceesingException {
		
		File file=new File("resource\\queryes.xml");
		XMLResultSet resultSet = new XMLProcessing(file);

		resultSet.setTagName("query");
		
		 table = new PrintTable<QueryModel>();
		
		
		table.addColumnHeader(new ColumnHeader(" QUERY ", 20));
		
		while (resultSet.next()) {
			QueryModel model=new QueryModel();
			
			model.setQuery(resultSet.getAttribute("id"));
			model.setParameters(getParamList(resultSet.getAllElement("parameters")));
			// System.out.println(resultSet.getAttribute("id"));
			table.addRowValue(model);
			table.addNewRow();
			

		}

		
		table.setBorderHorzontal('*');
		table.setBordervertical('*');
		table.print(true);
		
		
	}
	private static List<Parameter> getParamList(NodeList allElement) {
		 List<Parameter> parameters=new ArrayList<>();
		 for (int i = 0; i < allElement.getLength(); i++) {
			 
			 NodeList elm=((Element) allElement.item(i)).getElementsByTagName("parameter");
			 for (int j = 0; j < elm.getLength(); j++) {
				 Parameter parameter=new Parameter();
				 parameter.setName(((Element)elm.item(j)).getAttribute("name"));
				 parameter.setParameterType(ParameterType.valueOf(((Element)elm.item(j)).getAttribute("type")));
				 parameters.add(parameter);
			}
			 
		 }
		 
		return parameters;
	}

}

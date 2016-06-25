package com.rak.indix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rak.indi.models.Parameter;
import com.rak.indi.models.QueryModel;
import com.rak.indix.utils.ConnectionManeger;

public class QueryEngineExecuter {

	public static QueryResult executeQuery(QueryModel query) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		QueryResult queryResult=new QueryResult();
		try {
			 connection=ConnectionManeger.getConnection();
			  try {
				preparedStatement=connection.prepareStatement(query.getQuery());
				if(query.getParameters()!=null&&query.getParameters().size()>0){
					setParam(query.getParameters(),preparedStatement);
				}
				
				 try {
					resultSet=preparedStatement.executeQuery();
					setColumList(resultSet.getMetaData(),queryResult.getColumnList());
					while (resultSet.next()) {
						ArrayList<String> list=new ArrayList<>();
						
						for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
							list.add(resultSet.getString(i+1));
						}
						queryResult.getQueryResultList().add(list);
					}
					
				} catch (Exception e) {
					
					System.out.println("Unable to execute to query");
					e.printStackTrace();
				}
				
			} catch (Exception e) {
				System.out.println("Unable to create to query");
				e.printStackTrace();
			}finally {
				try{
					if(preparedStatement!=null){
						preparedStatement.close();
					}
				}catch(Exception e){
					
				}
			}
			 
		} catch (SQLException e) {
			System.out.println("Unable to connect to database:");
			e.printStackTrace();
		}finally {
			try{
				if(connection!=null){
				connection.close();
				}
			}catch(Exception e){
				
			}
		}
		return queryResult;
	}

	private static void setColumList(ResultSetMetaData metaData, ArrayList<String> columnList) throws SQLException {
		for (int i = 0; i < metaData.getColumnCount(); i++) {
			columnList.add(metaData.getColumnName(i+1));
		}
		
	}

	private static void setParam(List<Parameter> parameters, PreparedStatement preparedStatement) throws SQLException {
		
		for (int i = 0; i < parameters.size(); i++) {
			preparedStatement.setString(i+1, parameters.get(i).getValue());
		}
		
		
	}

}

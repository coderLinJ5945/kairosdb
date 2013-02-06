package net.opentsdb.datastore.h2.orm;

import java.util.*;
import genorm.runtime.*;

/**
	This class has been automatically generated by GenORMous.  This file
	should not be modified.
	
*/
public class Tag_base extends GenOrmRecord
	{
	protected static final Logger s_logger = LoggerFactory.getLogger(Tag.class.getName());

	public static final String COL_NAME = "name";
	public static final String COL_VALUE = "value";

	//Change this value to true to turn on warning messages
	private static final boolean WARNINGS = false;
	private static final String SELECT = "SELECT this.\"name\", this.\"value\" ";
	private static final String FROM = "FROM tag this ";
	private static final String WHERE = "WHERE ";
	private static final String KEY_WHERE = "WHERE \"name\" = ? AND \"value\" = ?";
	
	public static final String TABLE_NAME = "tag";
	public static final int NUMBER_OF_COLUMNS = 2;
	
	
	private static final String s_fieldEscapeString = "\""; 
	
	public static final GenOrmFieldMeta NAME_FIELD_META = new GenOrmFieldMeta("name", "string", 0, true, false);
	public static final GenOrmFieldMeta VALUE_FIELD_META = new GenOrmFieldMeta("value", "string", 1, true, false);

	
		
	//===========================================================================
	public static TagFactoryImpl factory = new TagFactoryImpl();
	
	public static interface TagFactory extends GenOrmRecordFactory
		{
		public boolean delete(String name, String value);
		public Tag find(String name, String value);
		public Tag findOrCreate(String name, String value);
		}
	
	public static class TagFactoryImpl //Inherit interfaces
			implements TagFactory 
		{
		public static final String CREATE_SQL = "CREATE CACHED TABLE tag (\n	\"name\" VARCHAR  NOT NULL,\n	\"value\" VARCHAR  NOT NULL,\n	PRIMARY KEY (\"name\", \"value\")\n	)";

		private ArrayList<GenOrmFieldMeta> m_fieldMeta;
		private ArrayList<GenOrmConstraint> m_foreignKeyConstraints;
		
		protected TagFactoryImpl()
			{
			m_fieldMeta = new ArrayList<GenOrmFieldMeta>();
			m_fieldMeta.add(NAME_FIELD_META);
			m_fieldMeta.add(VALUE_FIELD_META);

			m_foreignKeyConstraints = new ArrayList<GenOrmConstraint>();
			}
			
		protected Tag newTag(java.sql.ResultSet rs)
			{
			Tag rec = new Tag();
			((Tag_base)rec).initialize(rs);
			return ((Tag)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
	
		//---------------------------------------------------------------------------
		/**
			Returns a list of the feild meta for the class that this is a factory of
		*/
		public List<GenOrmFieldMeta> getFields()
			{
			return (m_fieldMeta);
			}

		//---------------------------------------------------------------------------
		/**
			Returns a list of foreign key constraints
		*/
		public List<GenOrmConstraint> getForeignKeyConstraints()
			{
			return (m_foreignKeyConstraints);
			}
			
		//---------------------------------------------------------------------------
		/**
			Returns the SQL create statement for this table
		*/
		public String getCreateStatement()
			{
			return (CREATE_SQL);
			}
			
		//---------------------------------------------------------------------------
		/**
			Creates a new entry with the specified primary keys.
		*/
		public Tag create(String name, String value)
			{
			Tag rec = new Tag();
			rec.m_isNewRecord = true;
			
			((Tag_base)rec).setName(name);
			((Tag_base)rec).setValue(value);

			
			return ((Tag)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
		//---------------------------------------------------------------------------
		/**
			Creates a new entry that is empty
		*/
		public Tag createRecord()
			{
			Tag rec = new Tag();
			rec.m_isNewRecord = true;
			
			return ((Tag)GenOrmDataSource.getGenOrmConnection().getUniqueRecord(rec));
			}
			
		//---------------------------------------------------------------------------
		/**
		If the table has a primary key that has a key generator this method will 
		return a new table entry with a generated primary key.
		@return Tag with generated primary key
		*/
		public Tag createWithGeneratedKey()
			{
			throw new UnsupportedOperationException("Tag does not support a generated primary key");
			}
			
		//---------------------------------------------------------------------------
		/**
		A generic api for finding a record.
		@param keys This must match the primary key for this record.  If the 
		record has multiple primary keys this parameter must be of type Object[] 
		where each element is the corresponding key.
		@return Tag or null if no record is found
		*/
		public Tag findRecord(Object keys)
			{
			Object[] kArr = (Object[])keys;
			return (find((String)kArr[0], (String)kArr[1]));
			}
			
		//---------------------------------------------------------------------------
		/**
			Deletes the record with the specified primary keys.
			The point of this api is to prevent a hit on the db to see if the record
			is there.  This call will add a record to the next transaction that is 
			marked for delete. 
			
			@return Returns true if the record was previous created and existed
			either in the transaction cache or the db.
		*/
		public boolean delete(String name, String value)
			{
			boolean ret = false;
			Tag rec = new Tag();
			
			((Tag_base)rec).initialize(name, value);
			GenOrmConnection con = GenOrmDataSource.getGenOrmConnection();
			Tag cachedRec = (Tag)con.getCachedRecord(rec.getRecordKey());
			
			if (cachedRec != null)
				{
				ret = true;
				cachedRec.delete();
				}
			else
				{
				rec = (Tag)con.getUniqueRecord(rec);  //This adds the record to the cache
				rec.delete();
				ret = rec.flush();
				rec.setIgnored(true); //So the system does not try to delete it again at commmit
				}
				
			return (ret);
			}
			
		//---------------------------------------------------------------------------
		/**
		Find the record with the specified primary keys
		@return Tag or null if no record is found
		*/
		public Tag find(String name, String value)
			{
			Tag rec = new Tag();
			
			//Create temp object and look in cache for it
			((Tag_base)rec).initialize(name, value);
			rec = (Tag)GenOrmDataSource.getGenOrmConnection().getCachedRecord(rec.getRecordKey());
			
			java.sql.PreparedStatement genorm_statement = null;
			java.sql.ResultSet genorm_rs = null;
			
			if (rec == null)
				{
				try
					{
					//No cached object so look in db
					genorm_statement = GenOrmDataSource.prepareStatement(SELECT+FROM+KEY_WHERE);
					genorm_statement.setString(1, name);
					genorm_statement.setString(2, value);

					s_logger.debug(genorm_statement.toString());
						
					genorm_rs = genorm_statement.executeQuery();
					if (genorm_rs.next())
						rec = newTag(genorm_rs);
					}
				catch (java.sql.SQLException sqle)
					{
					throw new GenOrmException(sqle);
					}
				finally
					{
					try
						{
						if (genorm_rs != null)
							genorm_rs.close();
							
						if (genorm_statement != null)
							genorm_statement.close();
						}
					catch (java.sql.SQLException sqle2)
						{
						throw new GenOrmException(sqle2);
						}
					}
				}
				
			return (rec);
			}
		
		//---------------------------------------------------------------------------
		/**
		This is the same as find except if the record returned is null a new one 
		is created with the specified primary keys
		@return A new or existing record.  
		*/
		public Tag findOrCreate(String name, String value)
			{
			Tag rec = find(name, value);
			if (rec == null)
				rec = create(name, value);
				
			return (rec);
			}
			
		//---------------------------------------------------------------------------
		/**
			Convenience method for selecting records.  Ideally this should not be use, 
			instead a custom query for this table should be used.
			@param where sql where statement.
		*/
		public ResultSet select(String where)
			{
			return (select(where, null));
			}
			
		//---------------------------------------------------------------------------
		/**
			Convenience method for selecting records.  Ideally this should not be use, 
			instead a custom query for this table should be used.
			@param where sql where statement.
			@param orderBy sql order by statement
		*/
		public ResultSet select(String where, String orderBy)
			{
			ResultSet rs = null;
			java.sql.Statement stmnt = null;
			
			try
				{
				stmnt = GenOrmDataSource.createStatement();
				StringBuilder sb = new StringBuilder();
				sb.append(SELECT);
				sb.append(FROM);
				if (where != null)
					{
					sb.append(WHERE);
					sb.append(where);
					}
					
				if (orderBy != null)
					{
					sb.append(" ");
					sb.append(orderBy);
					}
				
				String query = sb.toString();
				rs = new SQLResultSet(stmnt.executeQuery(query), query, stmnt);
				}
			catch (java.sql.SQLException sqle)
				{
				try
					{
					if (stmnt != null)
						stmnt.close();
					}
				catch (java.sql.SQLException sqle2) { }
					
				throw new GenOrmException(sqle);
				}
				
			return (rs);
			}
			
		
		//---------------------------------------------------------------------------
		/**
			Calls all query methods with test parameters.
		*/
		public void testQueryMethods()
			{
			ResultSet rs;
			}
		}
		
	//===========================================================================
	public static interface ResultSet extends GenOrmResultSet
		{
		public ArrayList<Tag> getArrayList(int maxRows);
		public ArrayList<Tag> getArrayList();
		public Tag getRecord();
		public Tag getOnlyRecord();
		}
		
	//===========================================================================
	private static class SQLResultSet 
			implements ResultSet
		{
		private java.sql.ResultSet m_resultSet;
		private java.sql.Statement m_statement;
		private String m_query;
		private boolean m_onFirstResult;
		
		//------------------------------------------------------------------------
		protected SQLResultSet(java.sql.ResultSet resultSet, String query, java.sql.Statement statement)
			{
			m_resultSet = resultSet;
			m_statement = statement;
			m_query = query;
			m_onFirstResult = false;
			}
		
		//------------------------------------------------------------------------
		/**
			Closes any underlying java.sql.Result set and java.sql.Statement 
			that was used to create this results set.
		*/
		public void close()
			{
			try
				{
				m_resultSet.close();
				m_statement.close();
				}
			catch (java.sql.SQLException sqle)
				{
				throw new GenOrmException(sqle);
				}
			}
			
		//------------------------------------------------------------------------
		/**
			Returns the reults as an ArrayList of Record objects.
			The Result set is closed within this call
			@param maxRows if the result set contains more than this param
				then an exception is thrown
		*/
		public ArrayList<Tag> getArrayList(int maxRows)
			{
			ArrayList<Tag> results = new ArrayList<Tag>();
			int count = 0;
			
			try
				{
				if (m_onFirstResult)
					{
					count ++;
					results.add(factory.newTag(m_resultSet));
					}
					
				while (m_resultSet.next() && (count < maxRows))
					{
					count ++;
					results.add(factory.newTag(m_resultSet));
					}
					
				if (m_resultSet.next())
					throw new GenOrmException("Bound of "+maxRows+" is too small for query ["+m_query+"]");
				}
			catch (java.sql.SQLException sqle)
				{
				sqle.printStackTrace();
				throw new GenOrmException(sqle);
				}
				
			close();
			return (results);
			}
		
		//------------------------------------------------------------------------
		/**
			Returns the reults as an ArrayList of Record objects.
			The Result set is closed within this call
		*/
		public ArrayList<Tag> getArrayList()
			{
			ArrayList<Tag> results = new ArrayList<Tag>();
			
			try
				{
				if (m_onFirstResult)
					results.add(factory.newTag(m_resultSet));
					
				while (m_resultSet.next())
					results.add(factory.newTag(m_resultSet));
				}
			catch (java.sql.SQLException sqle)
				{
				sqle.printStackTrace();
				throw new GenOrmException(sqle);
				}
				
			close();
			return (results);
			}
			
		//------------------------------------------------------------------------
		/**
			Returns the underlying java.sql.ResultSet object
		*/
		public java.sql.ResultSet getResultSet()
			{
			return (m_resultSet);
			}
			
		//------------------------------------------------------------------------
		/**
			Returns the current record in the result set
		*/
		public Tag getRecord()
			{
			return (factory.newTag(m_resultSet));
			}
			
		//------------------------------------------------------------------------
		/**
			This call expects only one record in the result set.  If multiple records
			are found an excpetion is thrown.
			The ResultSet object is automatically closed by this call.
		*/
		public Tag getOnlyRecord()
			{
			Tag ret = null;
			
			try
				{
				if (m_resultSet.next())
					ret = factory.newTag(m_resultSet);
					
				if (m_resultSet.next())
					throw new GenOrmException("Multiple rows returned in call from Tag.getOnlyRecord");
				}
			catch (java.sql.SQLException sqle)
				{
				throw new GenOrmException(sqle);
				}
				
			close();
			return (ret);
			}
			
		//------------------------------------------------------------------------
		/**
			Returns true if there is another record in the result set.
		*/
		public boolean next()
			{
			boolean ret = false;
			m_onFirstResult = true;
			try
				{
				ret = m_resultSet.next();
				}
			catch (java.sql.SQLException sqle)
				{
				throw new GenOrmException(sqle);
				}
			
			return (ret);
			}
		}
		
	//===========================================================================
		
	private GenOrmString m_name;
	private GenOrmString m_value;

	
	private List<GenOrmRecordKey> m_foreignKeys;
	
	public List<GenOrmRecordKey> getForeignKeys() { return (m_foreignKeys); }


	//---------------------------------------------------------------------------
	/**
	*/
	public String getName() { return (m_name.getValue()); }
	public Tag setName(String data)
		{
		boolean changed = m_name.setValue(data);
		
		//Add the now dirty record to the transaction only if it is not previously dirty
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(NAME_FIELD_META.getDirtyFlag());
			
			if (m_isNewRecord) //Force set the prev value
				m_name.setPrevValue(data);
			}
			
		return ((Tag)this);
		}
		

	//---------------------------------------------------------------------------
	/**
	*/
	public String getValue() { return (m_value.getValue()); }
	public Tag setValue(String data)
		{
		boolean changed = m_value.setValue(data);
		
		//Add the now dirty record to the transaction only if it is not previously dirty
		if (changed)
			{
			if (m_dirtyFlags.isEmpty())
				GenOrmDataSource.getGenOrmConnection().addToTransaction(this);
				
			m_dirtyFlags.set(VALUE_FIELD_META.getDirtyFlag());
			
			if (m_isNewRecord) //Force set the prev value
				m_value.setPrevValue(data);
			}
			
		return ((Tag)this);
		}
		
	
	
	
	
	//---------------------------------------------------------------------------
	protected void initialize(String name, String value)
		{
		m_name.setValue(name);
		m_name.setPrevValue(name);
		m_value.setValue(value);
		m_value.setPrevValue(value);

		}
		
	//---------------------------------------------------------------------------
	protected void initialize(java.sql.ResultSet rs)
		{
		try
			{
			if (s_logger.isDebug())
				{
				java.sql.ResultSetMetaData meta = rs.getMetaData();
				for (int I = 1; I <= meta.getColumnCount(); I++)
					{
					s_logger.debug("Reading - "+meta.getColumnName(I) +" : "+rs.getString(I));
					}
				}
			m_name.setValue(rs, 1);
			m_value.setValue(rs, 2);

			}
		catch (java.sql.SQLException sqle)
			{
			throw new GenOrmException(sqle);
			}
		}
	
	//---------------------------------------------------------------------------
	/*package*/ Tag_base()
		{
		super(TABLE_NAME);
		m_logger = s_logger;
		m_foreignKeys = new ArrayList<GenOrmRecordKey>();
		m_dirtyFlags = new java.util.BitSet(NUMBER_OF_COLUMNS);
		

		m_name = new GenOrmString(NAME_FIELD_META);
		addField(m_name);

		m_value = new GenOrmString(VALUE_FIELD_META);
		addField(m_value);

		GenOrmRecordKey foreignKey;
		}
	
	//---------------------------------------------------------------------------
	@Override
	public GenOrmConnection getGenOrmConnection()
		{
		return (GenOrmDataSource.getGenOrmConnection());
		}
		
	//---------------------------------------------------------------------------
	@Override
	public String getFieldEscapeString()
		{
		return (s_fieldEscapeString);
		}
		
	//---------------------------------------------------------------------------
	@Override
	public void setMTS()
		{
		}
		
	//---------------------------------------------------------------------------
	@Override
	public void setCTS()
		{
		}
		
	//---------------------------------------------------------------------------
	public String toString()
		{
		StringBuilder sb = new StringBuilder();
		
		sb.append("name=\"");
		sb.append(m_name.getValue());
		sb.append("\" ");
		sb.append("value=\"");
		sb.append(m_value.getValue());
		sb.append("\" ");

		
		return (sb.toString().trim());
		}
		
	//===========================================================================

	
	
	}
	
	
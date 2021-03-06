/**
 * Copyright 2017 Information and Computational Sciences,
 * The James Hutton Institute.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jhi.germinatedataimporter.database.entities;

import java.util.*;

import jhi.database.server.query.*;
import jhi.database.shared.exception.*;
import jhi.germinatedataimporter.util.*;
import jhi.germinatedataimporter.util.thread.*;

/**
 * @author Sebastian Raubach
 */
public class DatabaseDatabase
{
	public static final String HEADER_NAME = "Name";
	public static final String HEADER_ROWS = "Rows";

	private String name;

	private List<DatabaseTable> tables;

	public DatabaseDatabase(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	/**
	 * Returns all {@link DatabaseTable}s of the given database
	 *
	 * @return The {@link DatabaseTable}s
	 * @throws DatabaseException Thrown if the communication with the database fails
	 */
	public List<DatabaseTable> getTables() throws DatabaseException
	{
		if (tables != null)
		{
			new DatabaseTableScanThread(tables).start();
		}
		else
		{
			tables = new ArrayList<>();

			DatabaseObjectQuery.DatabaseObjectStreamer<DatabaseTable> streamer = new DatabaseObjectQuery<DatabaseTable>(String.format(SQLUtils.SELECT_TABLES_OF_DATABASE, name))
					.getStreamer(DatabaseTable.Parser.Instance.getInstance());

			DatabaseTable table;

			while ((table = streamer.next()) != null)
			{
				tables.add(table);
			}

			new DatabaseTableScanThread(tables).start();
		}

		return tables;
	}
}

/**
 * Copyright 2017 Sebastian Raubach and Paul Shaw from the Information and Computational Sciences Group at JHI Dundee
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */

package jhi.germinatedataimporter.util.exception;

import jhi.germinatedataimporter.database.entities.*;

/**
 * {@link MissingColumnException} extends {@link Exception} and is thrown if a column that cannot be null hasn't been mapped by the user
 *
 * @author Sebastian Raubach
 */
public class MissingColumnException extends Exception
{
	private static final long serialVersionUID = 8217661025452314212L;

	private DatabaseColumn column;

	public MissingColumnException(DatabaseColumn column)
	{
		super();
		this.column = column;
	}

	public MissingColumnException(String message)
	{
		super(message);
	}

	public MissingColumnException(Exception e)
	{
		super(e);
	}

	public DatabaseColumn getColumn()
	{
		return column;
	}
}
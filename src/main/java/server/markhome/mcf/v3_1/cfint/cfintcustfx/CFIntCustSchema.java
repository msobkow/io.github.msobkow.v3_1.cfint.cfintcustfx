// Description: Java 13 Cust JavaFX Schema.

/*
 *	server.markhome.mcf.CFInt
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFInt - Internet Essentials
 *	
 *	This file is part of Mark's Code Fractal CFInt.
 *	
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *	
 *	http://www.apache.org/licenses/LICENSE-2.0
 *	
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */

package server.markhome.mcf.v3_1.cfint.cfintcustfx;

import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.*;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import server.markhome.mcf.v3_1.cfsec.cfseccustfx.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;

/**
 *	The CFIntCustSchema defines the interface object that is
 *	provided by the cust interface for manipulating the CFInt
 *	facet in the user interface.
 */
public class CFIntCustSchema
extends CFSecCustSchema
implements ICFIntCustSchema
{
	public CFIntCustSchema() {
		super();
	}

	public ICFIntSchemaObj getCFIntSchema() {
		return( (ICFIntSchemaObj)schema );
	}

	public CFIntCustFacetPane newIntFacetPane( ICFFormManager formManager ) {
		CFIntCustFacetPane pane = newIntFacetPane( formManager, this );
		return( pane );
	}

	public CFIntCustFacetPane newIntFacetPane( ICFFormManager formManager, ICFIntCustSchema argSchema ) {
		CFIntCustFacetPane pane = new CFIntCustFacetPane( formManager, argSchema );
		return( pane );
	}
}

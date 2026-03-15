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

import javafx.geometry.Orientation;
import javafx.scene.layout.AnchorPane;

import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.*;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import server.markhome.mcf.v3_1.cfsec.cfseccustfx.*;

public class CFIntCustMainPane
extends AnchorPane
implements ICFSecAuthorizationCallbacks
{
	protected ICFIntCustSchema custSchema = null;
	protected CFSplitPane splitPane = null;
	protected CFTitledPane appConsoleTitledPane = null;
	protected CFConsole appConsole = null;
	protected CFTabPane tabPane = null;
	protected CFTabFormManager tabSec = null;
	protected CFTabFormManager tabInt = null;
	protected CFSecCustFacetPane paneSecFacet = null;
	protected CFIntCustFacetPane paneIntFacet = null;

	public CFIntCustMainPane(
		ICFIntCustSchema argSchema )
	{
		super();
		custSchema = argSchema;
		setMinHeight( 480 );
		setMinWidth( 800 );
		splitPane = new CFSplitPane();

		appConsoleTitledPane = new CFTitledPane();
		appConsoleTitledPane.setText( "Console Log" );

		appConsole = new CFConsole();
		appConsole.setMinHeight( 60 );
		appConsoleTitledPane.setContent( appConsole );

		tabPane = new CFTabPane();

		tabSec = new CFTabFormManager() {
			public void showRootMainForm() {
				if( paneSecFacet == null ) {
					paneSecFacet = new CFSecCustFacetPane( tabSec, argSchema );
				}
				tabSec.setRootForm( paneSecFacet );
			}
		};
		tabSec.setText( "Sec" );
		tabSec.setClosable( false );
		tabSec.setContent( paneSecFacet );
		tabPane.getTabs().add( tabSec );

		if( paneSecFacet == null ) {
			paneSecFacet = new CFSecCustFacetPane( tabSec, argSchema );
		}
		paneSecFacet.setAuthorizationCallbacks( this );

		tabInt = new CFTabFormManager() {
			public void showRootMainForm() {
				if( paneIntFacet == null ) {
					paneIntFacet = new CFIntCustFacetPane( tabInt, argSchema );
				}
				tabInt.setRootForm( paneIntFacet );
			}
		};

		tabInt.setText( "Int" );
		tabInt.setClosable( false );
		tabInt.setContent( paneIntFacet );
		tabInt.setDisable( true );
		tabPane.getTabs().add( tabInt );
		paneIntFacet = new CFIntCustFacetPane( tabInt, argSchema );
		tabInt.setRootForm( paneIntFacet );
		splitPane.setOrientation( Orientation.VERTICAL );
		splitPane.getItems().add( tabPane );
		splitPane.getItems().add( appConsoleTitledPane );
		setTopAnchor( splitPane, 0.0 );
		setLeftAnchor( splitPane, 0.0 );
		setRightAnchor( splitPane, 0.0 );
		setBottomAnchor( splitPane, 0.0 );
		getChildren().addAll( splitPane );
		tabInt.setDisable( true );

		paneIntFacet.showFacet();
		if( custSchema.getSchema().getAuthorization() != null ) {
			paneSecFacet.loggedIn();
			paneSecFacet.showSession();
		}
		else {
			paneSecFacet.showLogin();
		}
	}

	public ICFIntCustSchema getCustSchema() {
		return( custSchema );
	}

	public void setCustSchema( ICFIntCustSchema argSchema ) {
		final String S_ProcName = "setCustSchema";
		final String S_ArgNameSchema = "argSchema";
		if( argSchema == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				S_ArgNameSchema );
		}
		if( ! ( argSchema instanceof ICFIntCustSchema ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				S_ArgNameSchema,
				argSchema,
				"ICFIntCustSchema" );
		}
		custSchema = (ICFIntCustSchema)argSchema;
		if( paneSecFacet != null ) {
			paneSecFacet.setCustSchema( custSchema );
		}
		if( paneIntFacet != null ) {
			paneIntFacet.setCustSchema( custSchema );
		}
	}

	public void loggedIn() {
		tabSec.setDisable( false );
		tabInt.setDisable( false );
		paneIntFacet.showFacet();
	}

	public void preLogout() {
		tabSec.setDisable( false );
		tabInt.setDisable( true );
		paneIntFacet.showFacet();
	}
}

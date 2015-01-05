package org.beamfoundry.bundles.springdata.testjpa.eclipselink24.adapter;
import org.eclipse.persistence.platform.server.ServerPlatformBase;
import org.eclipse.persistence.sessions.DatabaseSession;

/**
 *
 */
public class EclipseLinkOSGiServerPlatform extends ServerPlatformBase {

    /**
     * c-tor
     *
     * @param databaseSession
     */
    public EclipseLinkOSGiServerPlatform(DatabaseSession databaseSession) {
        super(databaseSession);
    }

    @Override
    public Class getExternalTransactionControllerClass() {
        return EclipseLinkOSGiTransactionController.class;
    }
}
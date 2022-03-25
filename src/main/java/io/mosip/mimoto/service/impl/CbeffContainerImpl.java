package io.mosip.mimoto.service.impl;

import java.util.List;

import io.mosip.mimoto.entity.BIR;
import io.mosip.mimoto.entity.BIRInfo;
import io.mosip.mimoto.entity.BIRInfo.BIRInfoBuilder;
import io.mosip.mimoto.service.CbeffContainerI;
import io.mosip.mimoto.util.CbeffValidator;
import io.mosip.mimoto.util.CbeffXSDValidator;

/**
 * @author Ramadurai Pandian
 * 
 *         A Container Class where the BIR is created and updated
 *
 */
public class CbeffContainerImpl extends CbeffContainerI<BIR, BIR> {

    private BIR bir;

    /**
     * Method where the initialization of BIR happens
     * 
     * @param birList List of BIR data
     * @return BIR data with all images
     */
    @Override
    public BIR createBIRType(List<BIR> birList) {
        load();
        bir.setBirs(birList);
        return bir;
    }

    private void load() {
        // Creating first version of Cbeff
        bir = new BIR();
        // Initial Version
        // VersionType versionType = new VersionType();
        // versionType.setMajor(1);
        // versionType.setMinor(1);
        // VersionType cbeffVersion = new VersionType();
        // cbeffVersion.setMajor(1);
        // cbeffVersion.setMinor(1);
        // BIR.setVersion(versionType);
        // BIR.setCBEFFVersion(cbeffVersion);

        BIRInfoBuilder infoBuilder = new BIRInfoBuilder().withIntegrity(false);
        BIRInfo birInfo = new BIRInfo(infoBuilder);
        bir.setBirInfo(birInfo);
    }

    /**
     * Method to the update of BIR
     * 
     * @param birList   List of BIR data
     * 
     * @param fileBytes Cbeff XML data as bytes
     * 
     * @return BIR BIR data with all images
     */
    @Override
    public BIR updateBIRType(List<BIR> birList, byte[] fileBytes) throws Exception {
        BIR biometricRecord = CbeffValidator.getBIRFromXML(fileBytes);
        // BIR.getVersion().setMajor(BIR.getVersion().getMajor() + 1);
        // BIR.getCBEFFVersion().setMajor(BIR.getCBEFFVersion().getMajor());
        for (BIR bir : birList) {
            biometricRecord.getBirs().add(bir);
        }
        return biometricRecord;
    }

    /**
     * Method to the validate the BIR
     * 
     * @param xmlBytes Cbeff XML data as bytes array
     * 
     * @param xsdBytes Cbeff XSD data as bytes array
     * 
     * @return boolean of validated XML against XSD
     */
    @Override
    public boolean validateXML(byte[] xmlBytes, byte[] xsdBytes) throws Exception {
        return CbeffXSDValidator.validateXML(xsdBytes, xmlBytes);
    }

}

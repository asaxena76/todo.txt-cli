package in.vagabond.lms.modules.archive.impl;

import in.vagabond.lms.modules.archive.MdLsParser;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by asaxena on 4/12/17.
 */
public class TestMdlsParser {
    String mdlsout="_kMDItemOwnerUserID            = 501\n" +
        "kMDItemAcquisitionMake         = \"Apple\"\n" +
        "kMDItemAcquisitionModel        = \"iPhone 6 Plus\"\n" +
        "kMDItemAltitude                = 85.10775862068965\n" +
        "kMDItemAperture                = 2.275007124536905\n" +
        "kMDItemBitsPerSample           = 32\n" +
        "kMDItemColorSpace              = \"RGB\"\n" +
        "kMDItemContentCreationDate     = 2016-06-16 03:14:17 +0000\n" +
        "kMDItemContentModificationDate = 2016-06-16 03:14:17 +0000\n" +
        "kMDItemContentType             = \"public.jpeg\"\n" +
        "kMDItemContentTypeTree         = (\n" +
        "    \"public.jpeg\",\n" +
        "    \"public.image\",\n" +
        "    \"public.data\",\n" +
        "    \"public.item\",\n" +
        "    \"public.content\"\n" +
        ")\n" +
        "kMDItemCreator                 = \"9.3.2\"\n" +
        "kMDItemDateAdded               = 2017-04-13 01:23:09 +0000\n" +
        "kMDItemDisplayName             = \"2016-06-15 20.14.17.jpg\"\n" +
        "kMDItemEXIFVersion             = \"2.2.1\"\n" +
        "kMDItemExposureMode            = 0\n" +
        "kMDItemExposureProgram         = 2\n" +
        "kMDItemExposureTimeSeconds     = 0.06666666666666667\n" +
        "kMDItemFlashOnOff              = 0\n" +
        "kMDItemFNumber                 = 2.2\n" +
        "kMDItemFocalLength             = 4.15\n" +
        "kMDItemFSContentChangeDate     = 2016-06-16 03:14:17 +0000\n" +
        "kMDItemFSCreationDate          = 2016-06-16 03:14:17 +0000\n" +
        "kMDItemFSCreatorCode           = \"\"\n" +
        "kMDItemFSFinderFlags           = 0\n" +
        "kMDItemFSHasCustomIcon         = (null)\n" +
        "kMDItemFSInvisible             = 0\n" +
        "kMDItemFSIsExtensionHidden     = 0\n" +
        "kMDItemFSIsStationery          = (null)\n" +
        "kMDItemFSLabel                 = 0\n" +
        "kMDItemFSName                  = \"2016-06-15 20.14.17.jpg\"\n" +
        "kMDItemFSNodeCount             = (null)\n" +
        "kMDItemFSOwnerGroupID          = 20\n" +
        "kMDItemFSOwnerUserID           = 501\n" +
        "kMDItemFSSize                  = 1617913\n" +
        "kMDItemFSTypeCode              = \"\"\n" +
        "kMDItemGPSDateStamp            = \"2016:06:16\"\n" +
        "kMDItemGPSDestBearing          = 83.51004016064257\n" +
        "kMDItemHasAlphaChannel         = 0\n" +
        "kMDItemImageDirection          = 83.51004016064257\n" +
        "kMDItemISOSpeed                = 64\n" +
        "kMDItemKind                    = \"JPEG image\"\n" +
        "kMDItemLastUsedDate            = 2017-04-11 18:25:28 +0000\n" +
        "kMDItemLatitude                = 37.25156166666667\n" +
        "kMDItemLogicalSize             = 1617913\n" +
        "kMDItemLongitude               = -121.9460916666667\n" +
        "kMDItemOrientation             = 0\n" +
        "kMDItemPhysicalSize            = 1617920\n" +
        "kMDItemPixelCount              = 7990272\n" +
        "kMDItemPixelHeight             = 2448\n" +
        "kMDItemPixelWidth              = 3264\n" +
        "kMDItemProfileName             = \"sRGB IEC61966-2.1\"\n" +
        "kMDItemRedEyeOnOff             = 0\n" +
        "kMDItemResolutionHeightDPI     = 72\n" +
        "kMDItemResolutionWidthDPI      = 72\n" +
        "kMDItemSpeed                   = 0\n" +
        "kMDItemTimestamp               = \"03:14:17\"\n" +
        "kMDItemUseCount                = 1\n" +
        "kMDItemUsedDates               = (\n" +
        "    \"2017-04-11 07:00:00 +0000\"\n" +
        ")\n" +
        "kMDItemWhiteBalance            = 0";

    public void testParser() {
        MdLsParser.jsonFromMdls(mdlsout);
    }

    @Test
    public void testString() {


    }

}

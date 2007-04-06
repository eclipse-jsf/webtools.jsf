package org.eclipse.jst.jsf.core.tests.jsflibraryregistry.migration;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util.JSFLibraryRegistryUpgradeUtil;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.osgi.framework.Bundle;

/**
 * Tests a migration from a version 1 registry file to a version 2 file
 * 
 * @author cbateman
 *
 */
public class MigrationV1toV2Test extends TestCase 
{
   
    protected void setUp() throws Exception 
    {
        // JSFCorePlugin must already be active to ensure that it doesn't stop on
        // the test data when start is called
        assertEquals(Bundle.ACTIVE, JSFCorePlugin.getDefault().getBundle().getState());
        
        // clear plugin meta-data on every call
        clearRegistryFile(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL);
        clearRegistryFile(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL);
        clearRegistryFile(JSFLibraryRegistryUpgradeUtil.getBackupFileName(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL));
    }

    private void clearRegistryFile(String fileName) throws Exception
    {
        File file = getRegistryFile(fileName);
        
        if (file.exists())
        {
            assertTrue("Must be able to delete file: ".concat(fileName), file.delete());
        }
    }
    
    private File getRegistryFile(String fileName) throws Exception
    {
        URI uri = JSFTestUtil.getPlatformAbsPath(fileName);
        return new File(uri);
    }
    
    /**
     * Pre-condition: Empty Version 1 registry file
     * 
     * Expected post-conditions: 
     * 
     * 1) a successful upgrade should occur, but the upgraded flag should be false
     * 2) the backup file is successfully created
     */
    public void testEmptyFileMigration()
    {
        TestFileResource  emptyReg = new TestFileResource();
       
        try 
        {
            emptyReg.load(TestsPlugin.getDefault().getBundle(), "/testfiles/JSFLib/registryFiles/EmptyJSFLibraryRegistryV1.xml.data");
            JSFTestUtil.savePlatformRelative(emptyReg, JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL);
            assertTrue(JSFTestUtil.areEqual(emptyReg, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL)));
            
            JSFLibraryRegistryUpgradeUtil upgradeUtil = 
                JSFLibraryRegistryUpgradeUtil.getInstance();
            
            upgradeUtil.upgradeRegistryIfNecessary(2);
            assertEquals(IStatus.OK, upgradeUtil.getUpgradeStatus().getSeverity());
            assertEquals(false, upgradeUtil.getUpgradeStatus().isUpgradeOccurred());
            
            // ensure backup is successful
            assertTrue(JSFTestUtil.areEqual(emptyReg, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL.concat(".bkp"))));
            
            // in the case of empty file, the new v2 file must exist and match the v1 file after upgrade
            assertTrue(JSFTestUtil.areEqual(emptyReg, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL)));
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Pre-condition: Version 1 is present and valid with entries
     * 
     * Expected post-conditions:
     * 
     * 1) The V2 file will be created and populated with the old entries.
     * 2) Upgrade flag set.
     * 3) The ws will still need manual upgrade (not tested for)
     * 4) The v1 backup file will be created.
     * 
     */
    public void testValidNonEmptyMigration()
    {
        TestFileResource  validNonEmptyReg = new TestFileResource();
        
        try 
        {
            validNonEmptyReg.load(TestsPlugin.getDefault().getBundle(), "/testfiles/JSFLib/registryFiles/ValidNonEmptyJSFLibraryRegistryV1.xml.data");
            JSFTestUtil.savePlatformRelative(validNonEmptyReg, JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL);
            assertTrue(JSFTestUtil.areEqual(validNonEmptyReg, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL)));
            
            JSFLibraryRegistryUpgradeUtil upgradeUtil = 
                JSFLibraryRegistryUpgradeUtil.getInstance();
            
            upgradeUtil.upgradeRegistryIfNecessary(2);
            assertEquals(IStatus.OK, upgradeUtil.getUpgradeStatus().getSeverity());
            assertEquals(true, upgradeUtil.getUpgradeStatus().isUpgradeOccurred());
            
            // ensure backup is successful
            assertTrue(JSFTestUtil.areEqual(validNonEmptyReg
                    , JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.getBackupFileName(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL))));
            
            TestFileResource expectedV2File = new TestFileResource();
            expectedV2File.load(TestsPlugin.getDefault().getBundle(), "/testfiles/JSFLib/registryFiles/ValidNonEmptyJSFLibraryRegistryV1_expectedMigrationResult.xml.data");

            // in the case of empty file, the new v2 file must exist and match the v1 file after upgrade
            assertTrue(JSFTestUtil.areEqual(expectedV2File, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Pre-condition: No JSF Library Registry exists whatsoever
     * 
     * Expected post-conditions:
     * 
     * 1) No upgrade should occur
     * 2) No upgrade flag set
     * 3) Do nothing
     */
    public void testNoRegistryFileMigration()
    {
        
        JSFLibraryRegistryUpgradeUtil upgradeUtil = 
            JSFLibraryRegistryUpgradeUtil.getInstance();
        
        upgradeUtil.upgradeRegistryIfNecessary(2);
        
        // status should ok with no upgrade
        assertEquals(IStatus.OK, upgradeUtil.getUpgradeStatus().getSeverity());
        assertEquals(false, upgradeUtil.getUpgradeStatus().isUpgradeOccurred());
        
        // no file should be created
        try
        {
            File file = getRegistryFile(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL);
            assertFalse(file.exists());
            file = getRegistryFile(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL);
            assertFalse(file.exists());
            file = getRegistryFile(JSFLibraryRegistryUpgradeUtil.getBackupFileName(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL));
            assertFalse(file.exists());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Pre-condition: V2 library already exists as expected.  The V1 file is not present
     * 
     * Expected post-conditions:
     * 
     * 1) No upgrade should occur
     * 2) No upgrade flag set
     * 3) Do nothing
     */
    public void testV2AlreadyPresent_NoV1()
    {
        TestFileResource  validV2File = new TestFileResource();

        
        // no file should be created
        try
        {
            // load V2
            validV2File.load(TestsPlugin.getDefault().getBundle(), "/testfiles/JSFLib/registryFiles/ValidNonEmptyJSFLibraryRegistryV2.xml.data");
            JSFTestUtil.savePlatformRelative(validV2File, JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL);
            assertTrue(JSFTestUtil.areEqual(validV2File, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL)));

            JSFLibraryRegistryUpgradeUtil upgradeUtil = 
                JSFLibraryRegistryUpgradeUtil.getInstance();
            
            upgradeUtil.upgradeRegistryIfNecessary(2);
            
            // status should ok with no upgrade
            assertEquals(IStatus.OK, upgradeUtil.getUpgradeStatus().getSeverity());
            assertEquals(false, upgradeUtil.getUpgradeStatus().isUpgradeOccurred());

            //   No backup or v1.
            File file = getRegistryFile(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL);
            assertFalse(file.exists());
            file = getRegistryFile(JSFLibraryRegistryUpgradeUtil.getBackupFileName(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL));
            assertFalse(file.exists());

            // v2 file should be present and unchanged.
            file = getRegistryFile(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL);
            assertTrue(file.exists());
            assertTrue(JSFTestUtil.areEqual(validV2File, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL)));

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
    
    /**
     * Pre-condition: V2 library already exists as expected.  The V1 file is present
     * 
     * Expected post-conditions:
     * 
     * 1) No upgrade should occur
     * 2) No upgrade flag set
     * 3) Do nothing
     */
    public void testV2AlreadyPresent_WithV1()
    {
        TestFileResource  validV2File = new TestFileResource();
        TestFileResource  validV1File = new TestFileResource();
        
        // no file should be created
        try
        {
            // load V1
            validV1File.load(TestsPlugin.getDefault().getBundle(), "/testfiles/JSFLib/registryFiles/ValidNonEmptyJSFLibraryRegistryV1.xml.data");
            JSFTestUtil.savePlatformRelative(validV1File, JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL);
            assertTrue(JSFTestUtil.areEqual(validV1File, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL)));
            
            // load V2
            validV2File.load(TestsPlugin.getDefault().getBundle(), "/testfiles/JSFLib/registryFiles/ValidNonEmptyJSFLibraryRegistryV2.xml.data");
            JSFTestUtil.savePlatformRelative(validV2File, JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL);
            assertTrue(JSFTestUtil.areEqual(validV1File, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL)));

            JSFLibraryRegistryUpgradeUtil upgradeUtil = 
                JSFLibraryRegistryUpgradeUtil.getInstance();
            
            upgradeUtil.upgradeRegistryIfNecessary(2);
            
            // status should ok with no upgrade
            assertEquals(IStatus.OK, upgradeUtil.getUpgradeStatus().getSeverity());
            assertEquals(false, upgradeUtil.getUpgradeStatus().isUpgradeOccurred());

            // v1 and v2 files should be present. And should be unchanged
            File file = getRegistryFile(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL);
            assertTrue(file.exists());
            assertTrue(JSFTestUtil.areEqual(validV1File, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL)));

            file = getRegistryFile(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL);
            assertTrue(file.exists());
            assertTrue(JSFTestUtil.areEqual(validV1File, JSFTestUtil.getPlatformAbsPath(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL)));

            //   No backup or v1.
            file = getRegistryFile(JSFLibraryRegistryUpgradeUtil.getBackupFileName(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V1_URL));
            assertFalse(file.exists());


        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

}

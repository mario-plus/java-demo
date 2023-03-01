package com.mario.scan;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zxz
 * @date 2023年03月01日 10:58
 */
public class BoomServicePackageHolder {

    public static final String BEAN_NAME = "boomServicePackageHolder";

    private final Set<String> scannedPackages = new HashSet<>();

    private final Set<String> scannedClasses = new HashSet<>();


    public void addScannedPackage(String apackage) {
        apackage = normalizePackage(apackage);
        synchronized (scannedPackages) {
            scannedPackages.add(apackage);
        }
    }

    public boolean isPackageScanned(String packageName) {
        packageName = normalizePackage(packageName);
        synchronized (scannedPackages) {
            if (scannedPackages.contains(packageName)) {
                return true;
            }
            for (String scannedPackage : scannedPackages) {
                if (isSubPackage(packageName, scannedPackage)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addScannedClass(String className) {
        synchronized (scannedClasses) {
            scannedClasses.add(className);
        }
    }

    public boolean isClassScanned(String className) {
        synchronized (scannedClasses) {
            return scannedClasses.contains(className);
        }
    }

    /**
     * Whether test package is sub package of parent package
     * @param testPkg
     * @param parent
     * @return
     */
    private boolean isSubPackage(String testPkg, String parent) {
        // child pkg startsWith parent pkg
        return testPkg.startsWith(parent);
    }

    private String normalizePackage(String apackage) {
        if (!apackage.endsWith(".")) {
            apackage += ".";
        }
        return apackage;
    }
}

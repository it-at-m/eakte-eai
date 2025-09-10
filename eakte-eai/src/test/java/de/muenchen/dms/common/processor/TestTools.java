/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.processor;

import java.util.Arrays;

public class TestTools {
  public static Class<?>[] joinClassArrays(Class<?>[] arr1, Class<?>[] arr2) {
    Class<?>[] results = Arrays.copyOf(arr1, arr1.length + arr2.length);
    System.arraycopy(arr2, 0, results, arr1.length, arr2.length);
    return results;
  }
}

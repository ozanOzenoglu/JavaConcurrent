package com.company;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentNavigableMapSample implements Sample {
    @Override
    /*The headMap(T toKey) method returns a view of the map
    containing the keys which are strictly less than the given key.*/
    public void runSample() {
        ConcurrentNavigableMap map = new ConcurrentSkipListMap();
        map.put("1","one");
        map.put("2","two");
        map.put("3","three");
        ConcurrentNavigableMap headMap = map.headMap("2");
        /*
         * The headMap will point to a ConcurrentNavigableMap
         *  which only contains the key "1"
         , since only this key is strictly less than "2".
         * */
        System.out.println(headMap.get("1"));
        System.out.println(headMap.get("2")); // returns null
        /**
         * The tailMap will contain the keys "2" and "3" because
         * these two keys are greather than or equal to the given key, "2".
         */
        ConcurrentNavigableMap tailMap = map.tailMap("2");
        System.out.println(tailMap.get("2"));

        /*
        The returned submap contains only the key "2",
         because only this key is greater than or equal to "2",
          and smaller than "3".
        * */
        ConcurrentNavigableMap subMap = map.subMap("2","3");
        System.out.println(subMap.get("2"));
        System.out.println(subMap.get("3"));

    }
}

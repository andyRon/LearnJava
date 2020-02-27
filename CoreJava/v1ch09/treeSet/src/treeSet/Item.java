package treeSet;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author Andy Ron
 */
public class Item implements Comparable<Item> {

    private String description;
    private int partNumber;

    public Item(String aDescrition, int aPartNumber) {
        description = aDescrition;
        partNumber = aPartNumber;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" +
                "description='" + description + '\'' +
                ", partNumber=" + partNumber +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return partNumber == item.partNumber &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, partNumber);
    }

    @Override
    public int compareTo(Item o) {
        int diff = Integer.compare(partNumber, o.partNumber);
        return diff != 0 ? diff : description.compareTo(o.description);
    }
}

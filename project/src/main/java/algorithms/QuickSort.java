package algorithms;

import java.util.List;

import model.VenueModel;

public class QuickSort {
	private List<VenueModel> list;
    private int length;
 
    public List<VenueModel> sort(List<VenueModel> liste) {
         
        if (liste == null || liste.size() == 0) {
            return list;
        }
        this.list = liste;
        length = liste.size();
        quickSort(0, length - 1);
        return list;
    }
 
    private void quickSort(int lowerIndex, int higherIndex) {
         
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = list.get(lowerIndex+(higherIndex-lowerIndex)/2).getCheckinCount();
        // Divide into two arrays
        while (i <= j) {
            while (list.get(i).getCheckinCount() < pivot) {
                i++;
            }
            while (list.get(j).getCheckinCount() > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
 
    private void exchangeNumbers(int i, int j) {
        VenueModel temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
     
}

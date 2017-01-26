package algorithms;

import java.util.List;

import model.UserToUserModel;;

public class QuickSortUserToUser {

	private List<UserToUserModel> list;
    private int length;
 
    public List<UserToUserModel> sort(List<UserToUserModel> liste) {
         
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
        int pivot = list.get(lowerIndex+(higherIndex-lowerIndex)/2).getMatchedcount();
        // Divide into two arrays
        while (i <= j) {
            while (list.get(i).getMatchedcount() < pivot) {
                i++;
            }
            while (list.get(j).getMatchedcount() > pivot) {
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
    	UserToUserModel temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}

/*
Implement a method countSawtooth(arr) -> Similar to sawtooth sequence here but instead of strictly increasing/decreasing
we are dealing with odd-even numbers instead, and we have to have find all continuous subarrays of len >= 1.
*/

public class countSawtooth {

    public static int countSawtooth(int[] array){
        // there is atlas one subarray whose length is greater than zero, i.e., the array itself.
        int result = 1;

        int subArray = 1;

        for(int i = 1; i < array.length; i++){
            if(((array[i-1] % 2) ^ (array[i] % 2)) == 1){
                subArray += 1;
            }
            else{
                subArray = 1;
            }
            result += subArray;
        }


        return result;
    }

    public static void main(String[] args){
        int[] array = new int[] { 1,2,3,7,6,5 };

        int result = countSawtooth(array);
        System.out.println(result);
    }
}

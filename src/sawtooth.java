/*
Given an array of integers arr, your task is to count the number of contiguous subarrays that represent a sawtooth sequence of at least two elements.

For arr = [9, 8, 7, 6, 5], the output should be countSawSubarrays(arr) = 4. Since all the elements are arranged in decreasing order,
it won’t be possible to form any sawtooth subarray of length 3 or more. There are 4 possible subarrays containing two elements, so the answer is 4.

For arr = [10, 10, 10], the output should be countSawSubarrays(arr) = 0. Since all of the elements are equal, none of subarrays can be sawtooth, so the answer is 0.

For arr = [1, 2, 1, 2, 1], the output should be countSawSubarrays(arr) = 10.

All contiguous subarrays containing at least two elements satisfy the condition of the problem. There are 10 possible contiguous subarrays
containing at least two elements, so the answer is 10.
 */

/*
HINT: 3


This can be solved by just splitting the array into multiple sawtooth sequences..which is O(n) operation.
For example [1,2,1,3,4,-2] can be splitted into two sequence [1,2,1,3] and [3,4,-2] and now we just have to do C(size,2) operation for both the parts.

Answer:

    public int countSeq(int[] arr) {
        int len = arr.length;
        if (len < 2) {
          return 0;
        }

        int s = 0;
        int e = 1;
        int sign = arr[e] - arr[s];
        int count = 0;

        while (e < len) {
          while (e < len && arr[e] - arr[e-1] != 0 && isSameSign(arr[e] - arr[e-1], sign)) {
            sign = -1 * sign;
            e++;
          }
          // the biggest continue subsequence starting from s ends at e-1;
          int size = e - s;
          count = count + (size * (size - 1)/2); // basically doing C(size,2)
          s = e - 1;
          e = s + 1;
        }
        return count;
    }
*/


/*
I think the trick here is to realise that: assuming you have a valid sawtooth sequence of length x, adding one additional
valid element would increase the number of subsequences by x too.

Example:

[1,2,1] is a valid sawtooth sequence.

adding 2 to this valid sequence of [1,2,1] forms [1,2,1,2]. We see here that adding a new element to a valid sequence of length
 3 here adds 3 new valid subsequences which are: [1,2,1,2],[2,1,2], and [1,2].

Correspondingly, adding another valid element such as -1 to [1,2,1,2] would add 4 new subsequences which are: [1,2,1,2,-1], [2,1,2,-1],[1,2,-1],and [2,-1].

Thus, what we can use a moving window with left and right pointers l and r to keep track of the length of valid sequence,
resetting the l pointer when an invalid sequence is detected.

def solution(arr: list) -> int:
    '''
    for every char, check if still current sawtooth
    if still currently sawtooth, numberOfWays += length
    else reset temp counter
    '''
    l, r = 0, 1
    ways = 0
    while r < len(arr):

        # check if current char + past 2 chars are sawtooth
        if r-l > 1 and (arr[r-2] < arr[r-1] > arr[r] or
                        arr[r-2] > arr[r-1] < arr[r]):
            ways += r-l

        # check if current char + past 1 chars are sawtooth
        elif arr[r-1] != arr[r]:
            ways += 1
            l = r-1

        else:
            # reset left pointer
            l = r

        r += 1
    return ways

 */
public class sawtooth {

    public static int sawtoothSubarray(int[] arr){
        int answer = 0;
        int left = 0;
        int right = 1;

        while(right < arr.length){
            if(right - left > 1 && ((arr[right - 2] < arr[right - 1] && arr[right - 1] > arr[right]) || (arr[right - 2] > arr[right - 1] && arr[right - 1] < arr[right]))){
                System.out.println("first");
                answer += right-1;
            }
            else if(arr[right - 1] != arr[right]){
                System.out.println("second");
                answer += 1;
                left = right - 1;
            }
            else{
                // this case covers when two adjacent elements are equal, remember they are not considered a sawtooth sequence.
                left = right;
            }

            right++;
        }
        return answer;
    }
    public static void main(String[] args){
        int[] array = new int[]{1,4,5,7,6,5};
        int result = sawtoothSubarray(array);
        System.out.println(result);
    }
}

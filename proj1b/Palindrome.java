public class Palindrome {
    public Deque<Character> wordToDeque(String word){
          Deque<Character> deque = new LinkedListDeque<>();
          for (int i = 0; i < word.length(); i ++) {
              deque.addLast(word.charAt(i));
          }
          return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return helper(deque, 0,deque.size() - 1);
//        for (int i = 0; i < deque.size() / 2; i ++) {
//            if (deque.get(i) != deque.get(deque.size() - 1 - i)) return false;
//        }
//        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return  Offhelper(deque, 0, deque.size() - 1);
    }

    public boolean Offhelper(Deque<Character> deque, int i, int j) {
        OffByOne offByOne = new OffByOne();
        if (i >= j) return true;
        if (!offByOne.equalChars(deque.get(i),deque.get(j))) return false;
        return helper(deque, i + 1, j - 1);
    }

    public boolean helper(Deque<Character> deque,int i, int j ) {
        if (i >= j) return true;
        if (deque.get(i) != deque.get(j)) return false;
        return helper(deque, i + 1, j - 1);
    }


}

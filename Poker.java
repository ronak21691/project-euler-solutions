import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Poker {

	public static void main(String[] args) throws IOException {
		int count = 0;

		// read lines from file
		String fileName = "poker.txt";

		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String strLine;
		String cards[] = new String[10];
		while ((strLine = br.readLine()) != null) {
			cards = strLine.split(" ");
			Card[] player1 = new Card[5];
			Card[] player2 = new Card[5];
			for (int i = 0; i < 5; i++) {
				player1[i] = new Card(cards[i + 0]);
				player2[i] = new Card(cards[i + 5]);
			}

			// increment count if player 1 wins
			if (getScore(player1) > getScore(player2))
				count++;
		}
		System.out.println(count);
	}

	// method to get the winner of a play
	public static int getScore(Card[] player) {
		int[] rankCounts = new int[13];
		int flushSuit = player[0].suit;

		// how much time a card appeared
		for (Card card : player) {
			rankCounts[card.rank]++;
			if (card.suit != flushSuit)
				flushSuit = -1;
		}

		// how many different cards appeared same time
		int[] rankCountHist = new int[6];
		for (int i = 0; i < rankCounts.length; i++)
			rankCountHist[rankCounts[i]]++;

		int bestCards = get5FrequentHighestCards(rankCounts, rankCountHist);
		int straightHighRank = getStraightHighRank(rankCounts);

		if (straightHighRank != -1 && flushSuit != -1)
			return 8 << 20 | straightHighRank; // Straight flush
		else if (rankCountHist[4] == 1)
			return 7 << 20 | bestCards; // Four of a kind
		else if (rankCountHist[3] == 1 && rankCountHist[2] == 1)
			return 6 << 20 | bestCards; // Full house
		else if (flushSuit != -1)
			return 5 << 20 | bestCards; // Flush
		else if (straightHighRank != -1)
			return 4 << 20 | straightHighRank; // Straight
		else if (rankCountHist[3] == 1)
			return 3 << 20 | bestCards; // Three of a kind
		else if (rankCountHist[2] == 2)
			return 2 << 20 | bestCards; // Two pairs
		else if (rankCountHist[2] == 1)
			return 1 << 20 | bestCards; // One pair
		else
			return 0 << 20 | bestCards; // High card
	}
	
	//method to get the highest card which has frequency 
	// and give it a high result value
	private static int get5FrequentHighestCards(int[] ranks, int[] ranksHist) {
		int result = 0;

		for (int i = ranksHist.length - 1; i >= 0; i--) {
			for (int j = ranks.length - 1; j >= 0; j--) {
				if (ranks[j] == i) {
					for (int k = 0; k < i ; k++)
						result = result << 4 | j;
				}
			}
		}

		return result;
	}
	
	//this method returns the highest card from the hand
	private static int getStraightHighRank(int[] ranks) {
		outer: for (int i = ranks.length - 1; i >= 3; i--) {
			for (int j = 0; j < 5; j++) {
				if (ranks[(i - j + 13) % 13] == 0)
					continue outer;
			}
			return i;
		}
		return -1;
	}
}

// class for making player's card and suits in different instance
final class Card {

	public final int rank;
	public final int suit;

	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Card(String str) {
		this("23456789TJQKA".indexOf(str.charAt(0)), "SHCD".indexOf(str
				.charAt(1)));
	}

}

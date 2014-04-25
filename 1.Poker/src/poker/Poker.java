package poker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Poker {
	Player player;
	
	public static void main(String[] args) {
		Player player = new Player();
		printCards(player.getCards());
		
		System.out.println("交換するカードの番号を入力してください(例：135)。");
		System.out.println("0を入力するとカードを交換しません。");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean drawn = false; // 入力が正常に終わればtrue
		while (!drawn) {
			try {
				String input = reader.readLine();
				int[] idxs = toCardIndexArray(input);
				player.draw(idxs);
				drawn = true;
			} catch (IllegalArgumentException e) {
				System.err.println("指定が重複しています。");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("指定のカード番号は存在しません。");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		printCards(player.getCards());
		
		System.out.println("役は " + player.getHand().getName() + " でした。");
	}
	
	/**
	 * ユーザーからの入力文字列をカード配列用のint[]型の配列に変換する
	 * @param replaceNums ユーザーの入力文字列 
	 * @return カードのインデックスに対応するint[]型配列
	 */
	private static int[] toCardIndexArray(String replaceNums) throws NumberFormatException {
		if (replaceNums.equals("0")) {
			return new int[0];
		}

		int[] idxs = new int[replaceNums.length()];
		for (int i = 0; i < replaceNums.length(); i++) {
			String idxString = replaceNums.substring(i, i+1);
			idxs[i] = Integer.parseInt(idxString) - 1; // 入力とインデックスは1ずれる
		}
		return idxs;
	}
	
	private static void printCards(Card[] cards) {
		for (int i = 0; i < cards.length; i++) {
			Card card = cards[i];
			System.out.println((i+1) + ":" + card);
		}
	}
}

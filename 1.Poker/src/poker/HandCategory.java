package poker;

/**
 * @author a13456
 * 役の種類を表すための列挙型
 */
public enum HandCategory {
	NO_PAIR("ノーペア"),
	ONE_PAIR("ワンペア"),
	TWO_PAIR("ツーペア"),
	THREE_OF_KIND("スリーカード"),
	STRAIGHT("ストレート"),
	ROYAL_STRAIGHT("ロイヤルストレート"),
	FLUSH("フラッシュ"),
	FULL_HOUSE("フルハウス"),
	FOUR_OF_KIND("フォーカード"),
	STRAIGHT_FLUSH("ストレートフラッシュ"),
	ROYAL_STRAIGHT_FLUSH("ロイヤルストレートフラッシュ");
	
	private String name;
	
	private HandCategory(String name) {
		this.name = name;
	}
	
	/** 
	 * 役の種類を日本語で取得する
	 * @return 役の種類の日本語名
	 */
	public String getName() {
		return name;
	}
}

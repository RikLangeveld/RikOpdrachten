using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class PlayerUI : MonoBehaviour {

    public Text strongBulletStackText;
    public Text gravityBulletStackText;
    public Image selectionBorder;

    PlayerWeapon playerWeapon;

    private int adjustBulletSelectBorder = 65;

	// Use this for initialization
	void Start () {
        playerWeapon = GetComponent<PlayerWeapon>();

        UpdateBulletStacksUI();
	}
	
	// Update is called once per frame
	public void UpdateBulletStacksUI () {
        gravityBulletStackText.text = playerWeapon.GravityBulletStack.ToString();
        strongBulletStackText.text =  playerWeapon.StrongBulletStack.ToString();
    }

    public void UpdateBulletSelectBorder(float adjustXPosition) {
        adjustXPosition *= adjustBulletSelectBorder;
        selectionBorder.GetComponent<RectTransform>().anchoredPosition += new Vector2(adjustXPosition, 0);
    }
}

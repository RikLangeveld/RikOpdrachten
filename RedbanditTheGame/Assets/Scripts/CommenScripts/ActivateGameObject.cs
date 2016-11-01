using UnityEngine;
using System.Collections;

public class ActivateGameObject : MonoBehaviour {

	// Use this for initialization
	void Start () {
        GameObject gm = gameObject;
        gm.SetActive(true);
	}
}

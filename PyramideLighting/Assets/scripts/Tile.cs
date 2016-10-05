using UnityEngine;
using System.Collections;

public class Tile : MonoBehaviour {

    public GameObject tile;

    public Tile(GameObject obj, int row, int col, int size)
    {
        Instantiate(obj);
        this.tile = obj;
        tile.transform.position = new Vector3(row * size, 0, col * size);
    }
}

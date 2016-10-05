using UnityEngine;
using System.Collections;

public class MapBuilder : MonoBehaviour {

    public GameObject wall;
    public GameObject floor;


    int[,] map = new int[5, 5]{
            {1,1,1,1,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,1,1,1,1}
        };
    // Use this for initialization
    void Start () {

       int rows = map.GetLength(0);
       int cols = map.GetLength(1);

        for (var r = 0; r < rows; r++)
        {
            for (var c = 0; c < cols; c++)
            {
                switch (map[r,c])
                {
                    case (0):
                        new CreateWall(wall, r, c, 10);
                        break;
                    case (1):
                        new CreateWall(wall, r, c, 10);
                           break;
                }
            }
        }
    }
}

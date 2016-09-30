using UnityEngine;
using System.Collections.Generic;

public class Node {

    /* Dit is een Node, een soort tegel van het geheel. */

    public List<Node> adjecent = new List<Node>();
    public Node previous = null;
    public string label = "";

    public void Clear()
    {
        previous = null;
    }

}

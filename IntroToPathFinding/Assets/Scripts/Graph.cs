using UnityEngine;
using System.Collections;

public class Graph {

    /* In deze graph worden alle nodes gemaakt en wordt de label van de node naar een waarde gezet. */

    public int rows = 0;
    public int cols = 0;
    public Node[] nodes;

    public Graph(int[,] grid)
    {
        rows = grid.GetLength(0);
        cols = grid.GetLength(1);

        nodes = new Node[grid.Length];

        for (int i = 0; i < nodes.Length; i++)
        {
            Node node = new Node();
            node.label = i.ToString();
            nodes[i] = node;
        }

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
                {
                    var node = nodes[this.cols * r + c];

                    if (grid[r,c] == 1)
                    {
                        continue;
                    }

                    //up
                    if (r > 0)
                    {
                    node.adjecent.Add(nodes[this.cols * (r - 1) + c]);
                    }

                    if (c < cols - 1)
                    {
                        node.adjecent.Add(nodes[this.cols * r + c + 1]);
                    }

                    if (r < rows-1)
                    {
                        node.adjecent.Add(nodes[this.cols*(r+1)+c]);
                    }

                    if (c > 0)
                    {
                        node.adjecent.Add(nodes[this.cols*r+c-1]);
                    }

            }
        }
    }
}

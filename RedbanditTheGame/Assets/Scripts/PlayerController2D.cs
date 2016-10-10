using UnityEngine;
using System.Collections;

public class PlayerController2D : MonoBehaviour {

    public int moveSpeed;
    public int jumpHeight;
    public int extraJumps;
    public GameObject graphics;

    public Transform groundPoint;
    public float radius;
    public LayerMask groundMask;

    bool isGrounded;
    Rigidbody2D rb2D;
    int originalExtraJumps;

	// Use this for initialization
	void Start () {
        rb2D = GetComponent<Rigidbody2D>();
        originalExtraJumps = extraJumps;
	}
	
	// Update is called once per frame
	void Update () {
        Vector2 moveDir = new Vector2(Input.GetAxisRaw("Horizontal") * moveSpeed, rb2D.velocity.y);
        rb2D.velocity = moveDir;

        if (Input.GetAxisRaw("Horizontal") != 0)
        {
            graphics.GetComponent<Animator>().SetBool("isMoving", true);
            flipImage();
        }
        else
        {
            graphics.GetComponent<Animator>().SetBool("isMoving", false);
        }

        if (Input.GetKeyDown(KeyCode.Space) && extraJumps > 0)
        {
            extraJumps--;
            rb2D.velocity = new Vector2(rb2D.velocity.x, 0); ;
            rb2D.AddForce(new Vector2(0, jumpHeight * 100));
        }

        //checks if player is on the ground
        isGrounded = Physics2D.OverlapCircle(groundPoint.position, radius, groundMask);
        if (isGrounded)
        {
            graphics.GetComponent<Animator>().SetBool("isJumping", false);
            extraJumps = originalExtraJumps;
        }
        else
        {
            graphics.GetComponent<Animator>().SetBool("isJumping", true);
        }
	}


    // zet de grapics van de speler in de juiste richting.
    void flipImage ()
    {
        if (Input.GetAxisRaw("Horizontal") == 1)
        {
            transform.localScale = new Vector3(1, 1, 1);
        }
        else if (Input.GetAxisRaw("Horizontal") == -1)
        {
            transform.localScale = new Vector3(-1, 1, 1);
        }
    }

}

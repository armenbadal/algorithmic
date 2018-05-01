
package ast;

public class Scalar implements Type {
    public char id;

    public Scalar( char e )
    {
        id = e;
    }

	@Override
	public String toString()
	{
		if( id == 'L' )
			return "տրամ";

		if( id == 'I' )
			return "ամբ";

		if( id == 'R' )
			return "իրկ";

		if( id == 'T' )
			return "տեքստ";
				
		return "";
	}
}



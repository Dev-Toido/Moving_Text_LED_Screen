package LedScreen;

public class LED {
	private boolean i,j;
	//default constructor
	LED(){
		this.i=false; this.j=false;
	}
	//functions to change the values of the i and j
	void setI(boolean i){ this.i=i;	}
	void setJ(boolean j){ this.j=j;	}

	//just to on or off simply
	void on(){		this.i=true; this.j=true;	}
	void off(){		this.i=false; this.j=false;	}

	//to invert the LedScreen.LED status
	void toggle(){
		this.i=!this.i;
		this.j=!this.j;
	}

	//Check of the status
	boolean isOn(){		return this.i && this.j;	}
}

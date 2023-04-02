package methods;
import java.util.Random;
import apis.ABBTDA;
import apis.ColaPrioridadTDA;
import apis.ColaTDA;
import apis.ConjuntoTDA;
import apis.DiccionarioMultipleTDA;
import apis.DiccionarioSimpleTDA;
import apis.GrafoTDA;
import apis.PilaTDA;
import impl.ABB;
import impl.ColaLD;
import impl.ColaPU;
import impl.ColaPrioridadDA;
import impl.ConjuntoLD;
import impl.ConjuntoTA;
import impl.DicSimpleL;
import impl.PilaLD;
import impl.PilaTI;
import impl.PilaTF;

public class Metodos {

	
	// ---------------------------------------------------------- Pasar --------------------------------
		
		public static void pasar(PilaTDA p1, PilaTDA p2) {
			while(!p1.pilaVacia()) {
				p2.apilar(p1.tope());
				p1.desapilar();
			}
			
		}
		public static void pasar(ColaTDA c1, ColaTDA c2) {
			while(!c1.colaVacia()) {
				c2.acolar(c1.primero());
				c1.desacolar();
			}		
		}
		
		public static void pasar(PilaTDA p1, ColaTDA c2) {
			while(!p1.pilaVacia()) {
				int x=p1.tope();
				c2.acolar(x);
				p1.desapilar();
			}
			
		}
		public static void pasar(ColaPrioridadTDA cola, ColaPrioridadTDA c) {
			while (!cola.colaVacia()) {
				c.acolarPrioridad(cola.primero(), cola.prioridad());				
				cola.desacolar();
			}
		}
		
		
		public static void pasar (ColaTDA c1, PilaTDA p1) {
			
			while(!c1.colaVacia()) {
				p1.apilar(c1.primero());
				c1.desacolar();
			}
		}
		


		public static void pasar(ConjuntoTDA o, ConjuntoTDA d) {
			while (!o.conjuntoVacio()) {
				int e=o.elegir();
				d.agregar(e);
				o.sacar(e);
			}
		}
		
		
	// ---------------------------------------------------- copiar -----------------------------------
		
		public static void copiar(PilaTDA p1, PilaTDA p2) {	
				PilaTDA aux = new PilaTI();
				aux.inicializarPila();
				pasar(p1, aux);
			while(!aux.pilaVacia()) {
				p2.apilar(aux.tope());
				p1.apilar(aux.tope());
				aux.desapilar();
			}
			
		}
		
		public static void copiar(ColaTDA origen, ColaTDA destino) {
			ColaTDA aux = new ColaPU();
			aux.inicializarCola();
			pasar(origen,aux);
			while(!aux.colaVacia()) {
				origen.acolar(aux.primero());
				destino.acolar(aux.primero());
				aux.desacolar();
			}
		}
		public static void copiar(ColaPrioridadTDA c1, ColaPrioridadTDA c2) {
			ColaPrioridadTDA aux = new ColaPrioridadDA();
			aux.inicializarCola();
			pasar(c1, aux);
			while(!aux.colaVacia()) {
				c1.acolarPrioridad(aux.primero(), aux.prioridad());
				c2.acolarPrioridad(aux.primero(), aux.prioridad());
				aux.desacolar();
			}
			
		}



		public static void copiar(ConjuntoTDA o, ConjuntoTDA d) {
			ConjuntoTDA aux= new ConjuntoTA();

			aux.inicializarConjunto();
			while(!o.conjuntoVacio()){
				int e=o.elegir();
				aux.agregar(e);
				d.agregar(e);
				o.sacar(e);
			

			}
			pasar(aux,o);
		}

		
	// ---------------------------------------------------- Pilas -----------------------------------	
		
		public static boolean mismosElementos(PilaTDA p, ColaTDA c) {
			PilaTDA paux=new PilaTF();
			paux.inicializarPila();
			ColaTDA caux=new ColaPU();
			caux.inicializarCola();
			copiar(p,paux);
			copiar(c,caux);
			ConjuntoTDA cc =new ConjuntoTA();
			cc.inicializarConjunto();
			ConjuntoTDA cp =new ConjuntoTA();
			cp.inicializarConjunto();
			
			while(!paux.pilaVacia()) {
				cp.agregar(paux.tope());
				paux.desapilar();	
			}
			
			while(!caux.colaVacia()) {
				cc.agregar(caux.primero());
				caux.desacolar();	
			}
			return sonIguales1(cp,cc); 
		}
		
		public static void sinRepeticiones(PilaTDA P) {
			PilaTDA aux = new PilaTF();
			aux.inicializarPila();
			PilaTDA fin = new PilaTF();
			fin.inicializarPila();
			int comparador = 0;
			while (!P.pilaVacia()) {
				while (!P.pilaVacia()) {
					aux.apilar(P.tope());
					P.desapilar();
				}
				comparador = aux.tope();
				aux.desapilar();
				while (!aux.pilaVacia()) {
					/*
					 * en este ciclo comparo el tope de la pila (guardado en la variable
					 * "comparador") con los demas valores de la pila. Si estos difieren lo vuelvo a
					 * apilar en la pila original (P) Si no hay diferencia, desapilo el valor dado
					 * que se tiene que respetar el orden de los elementos repetidos
					 */
					if (comparador != aux.tope())
						P.apilar(aux.tope());
					aux.desapilar();
				}
				fin.apilar(comparador);
			}
			// volvemos a ingresar los datos sin los valores repetidos en la pila original
			while (!fin.pilaVacia()) {
				P.apilar(fin.tope());
				fin.desapilar();
			}
			invertirPila(P);
		}
		
		
		public static float sumar(PilaTDA p1) {
			PilaTDA aux=new PilaTF();
			aux.inicializarPila();
			copiar(p1,aux);
			int sum=0; 
			while(!aux.pilaVacia()) {
				sum+=aux.tope();
				aux.desapilar();	
			}
			return sum; 
		}
		

	
		public static float promedio(PilaTDA p1) {
			PilaTDA aux=new PilaTF();
			aux.inicializarPila();
			copiar(p1,aux);
			float sum=0; // Lo declaro float para que al dividir tome el tipo float
			int cont=0;
			while(!aux.pilaVacia()) {
				cont++;
				sum+=aux.tope();
				aux.desapilar();	
			}
			return sum/cont; // Como sum es float y cont es int, el cociente queda float
		}


	// ------------------------------------------------------ Cola -----------------------------------


		public static boolean esCapicua(ColaTDA c) {
			PilaTDA p=new PilaTF();
			p.inicializarPila();
			ColaTDA caux=new ColaPU();
			caux.inicializarCola();		
			int cont=0;
			while(!c.colaVacia()) {
				p.apilar(c.primero());caux.acolar(c.primero());
				c.desacolar();cont++;
			}
			copiar(caux,c);
			int mitad=cont/2;	
			while(!caux.colaVacia() &&  mitad > 0 && caux.primero()==p.tope() ) {
				caux.desacolar();p.desapilar();	
				mitad--;		
			}
			return cont > 0 && mitad==0  ;
			
		}
		
		public static void convertirPilaCola (PilaTDA p1, ColaTDA c1) {
			
			while(!p1.pilaVacia()) {
				c1.acolar(p1.tope());
				p1.desapilar();
			}
		}
		
		public static void convertirColaPila(ColaTDA c1, PilaTDA p1) {
			
			while(!c1.colaVacia()) {
				p1.apilar(c1.primero());
				c1.desacolar();
			}
			
		}
		
		
		public static void convertirPilaConjunto (PilaTDA p, ConjuntoTDA cj) {
			
			while(!p.pilaVacia()) {
				cj.agregar(p.tope());
				p.desapilar();
			}
			
		}
		
		
		public static ColaPrioridadTDA combinarColasP (ColaPrioridadTDA c1, ColaPrioridadTDA c2) {
			
			ColaPrioridadTDA c3 = new ColaPrioridadDA();
			c3.inicializarCola();
			while (!c1.colaVacia()) {
				c3.acolarPrioridad(c1.primero(),c1.prioridad());
				c1.desacolar();
			}
			
			while(!c2.colaVacia()) {
				
				c3.acolarPrioridad(c2.primero(), c2.prioridad());
				c2.desacolar();
			}
			
			return c3;
		}
		
			
		
		public static void colasIguales (ColaTDA c1, ColaTDA c2) {
			// Se asume que tienen la misma cantidad de elementos
			int v1 = 0;
			int v2 = 0;
			while (!c1.colaVacia()) {
				v1=c1.primero();
				v2=c2.primero();
				c1.desacolar();
				c2.desacolar();
				
				if (v1 != v2) {
					System.out.println("Las colas no son iguales");
					break;
				}
			}
			System.out.println("La cola 1 invertida es igual a la cola 2");
		}
		
		
			
		public void eliminarRepetidos(PilaTF pila) {
			PilaTF aux = new PilaTF();
			aux.inicializarPila();
			pasar(pila,aux);
			ConjuntoTDA c=new ConjuntoTA();
			c.inicializarConjunto();
	    	while(!aux.pilaVacia()){
	    		if ( !c.pertenece(aux.tope()) ){
	    			 c.agregar(aux.tope()); 
	    			 pila.apilar(aux.tope());  			 
	    		}
	  			aux.desapilar();
	    	}			
		}
		
		
		public static void invertirPila(PilaTDA p) {
			ColaTDA c=new ColaPU();
			c.inicializarCola();
			// Paso de la pila a la cola
	        while (!p.pilaVacia()) {
	            int tope = p.tope();
	            c.acolar(tope);
	            p.desapilar();
	        }
	        // Paso de la cola a la pila (se invierte) 
	        while (!c.colaVacia()) {
	            int primero = c.primero();
	            p.apilar(primero);
	            c.desacolar();
	        }
	    }
		
		public static int contarElementosPila (PilaTDA p1) {
			
			PilaTDA paux = new PilaTF();
			paux.inicializarPila();
			copiar(p1,paux);
			int cont =0;
			while(!paux.pilaVacia()) {
				cont++;
				paux.desapilar();
			}
			
			return cont;
			
		}
		
		
		
		public static void invertirCola(ColaTDA c) {
			ColaTDA a=new ColaPU();
			a.inicializarCola();
			ColaTDA b=new ColaPU();
			b.inicializarCola();
			// Paso de la cola a la cola b
			int N=0;
	        while (!c.colaVacia()) {
	            int primero = c.primero();
	            c.desacolar();
	            a.acolar(primero);
	            N++;
	        }
	       
	        while (N>1) {
	        	for(int i=1;i<N;i++) {// Pasa a b todo menos el últmo elemento
	        		b.acolar(a.primero());
	        		a.desacolar();       		
	        	}
	        	c.acolar(a.primero()); // Pasa a c el último.
	        	a.desacolar();
	        	N--;
	        	ColaTDA aux=a; 
	        	a=b;b=aux;// Intercambio a con b.
	        }
	        c.acolar(a.primero());
	    }
		
		
		public static void invertirColaRecursivo(ColaTDA c) {
	        if (!c.colaVacia()) {
	            int primero = c.primero();
	            c.desacolar();
	            invertirColaRecursivo(c);
	            c.acolar(primero);
	        }
	    }
	
		public static void invertirColaConPila(ColaTDA c) {
			PilaTDA p=new PilaTF();
			p.inicializarPila();
			// Paso de la cola a la Pila p (se invierte)
	        while (!c.colaVacia()) {
	            int primero = c.primero();
	            c.desacolar();
	            p.apilar(primero);
	        }
	        // Paso de la pila p a la cola 
	        while (!p.pilaVacia()) {
	            int tope = p.tope();
	            c.acolar(tope);
	            p.desapilar();
	        }
	    }
		
		
		public static void elementosFinales (ColaTDA c1 , ColaTDA c2) {
			
			int valorfinalc1 = 0; 
			int valorfinalc2=0;
			
			while (!c1.colaVacia()) {
				valorfinalc1 = c1.primero();
				c1.desacolar();
			}
			
			while (!c2.colaVacia()) {
				valorfinalc2 = c2.primero();
				c2.desacolar();
			}
			
			if (valorfinalc1 == valorfinalc2) {
				System.out.println("Los elementos finales son iguales");
			}
			
			else 
				System.out.println("Los elementos finales NO son iguales");
				
		}
		
		
		public static boolean sonIguales1(ConjuntoTDA a, ConjuntoTDA b) { 
		ConjuntoTA aux1= new ConjuntoTA();
		aux1.inicializarConjunto();
		ConjuntoTA aux2= new ConjuntoTA();
		aux2.inicializarConjunto();
		copiar(a,aux1);
		copiar(b,aux2);
		boolean iguales=true;
		int elemento;
		while(!aux1.conjuntoVacio() && !aux2.conjuntoVacio() && iguales) {
			elemento=aux1.elegir();
			if(!aux2.pertenece(elemento))iguales=false;
			else{aux1.sacar(elemento);
			aux2.sacar(elemento);
			}
			}
		
		return(aux1.conjuntoVacio() && aux2.conjuntoVacio());
		}
			
		public static void cardinalidadCj(ConjuntoTDA cj) {
			
			ConjuntoTDA cjaux = new ConjuntoLD();
			cjaux.inicializarConjunto();
			copiar(cj, cjaux);
			int cont= 0;
			while (!cjaux.conjuntoVacio()) {
				cont++;
				int valor = cjaux.elegir();
				cjaux.sacar(valor);
			}
			System.out.print("La cardinalidad del conjunto es: ");
			System.out.println(cont);
			
		}
		
		public static ConjuntoTDA diferencia(ConjuntoTDA a, ConjuntoTDA b) {
			ConjuntoTA aux1= new ConjuntoTA();
			aux1.inicializarConjunto();
			copiar(a,aux1);
			ConjuntoTA aux2= new ConjuntoTA();
			aux2.inicializarConjunto();
			copiar(a,aux2);
			
			while(!aux1.conjuntoVacio()) {
				int e=aux1.elegir();
				if (b.pertenece(e)) 
					aux2.sacar(e);
				
				aux1.sacar(e);
			}
			return aux2;
		}
			
		public static ConjuntoTDA union(ConjuntoTDA a, ConjuntoTDA b) {
			ConjuntoTDA aux= new ConjuntoTA();
			aux.inicializarConjunto();
			copiar(a,aux);
			copiar(b,aux);
			return aux;
			
		}
		public static ConjuntoTDA interseccion(ConjuntoTDA a, ConjuntoTDA b) {
			ConjuntoTA aux1= new ConjuntoTA();
			aux1.inicializarConjunto();
			ConjuntoTA aux2= new ConjuntoTA();
			aux2.inicializarConjunto();
			while (!a.conjuntoVacio()) {
				int e=a.elegir();
				if (b.pertenece(e)) {
					aux1.agregar(e);
				}
				aux2.agregar(e);
				a.sacar(e);
			}
			pasar(aux2,a);
			return aux1;
		}
		
		
		public static ConjuntoTDA diferenciaSimetricaSinOperaciones(ConjuntoTDA a, ConjuntoTDA b) {
			ConjuntoTA aux1= new ConjuntoTA();
			aux1.inicializarConjunto();
			ConjuntoTA aux2= new ConjuntoTA();
			aux2.inicializarConjunto();
			ConjuntoTA aux3= new ConjuntoTA();
			aux3.inicializarConjunto();
			copiar(a,aux1);
			copiar(b,aux3);
			while(!aux1.conjuntoVacio()) {
				int e=aux1.elegir();
				if (b.pertenece(e)) 
				    aux3.sacar(e);
				else
					aux2.agregar(e);
				aux1.sacar(e);
			}		
			return union(aux2,aux3);
		}
		
		public static ConjuntoTDA diferenciaSimetricaUnionDiferencia(ConjuntoTDA a, ConjuntoTDA b) {	
			return union(diferencia(a,b), diferencia(b,a));
		}
		
		
		public static ConjuntoTDA diferenciaSimetricaUnionInterseccionDiferencia(ConjuntoTDA a, ConjuntoTDA b) {	
			return diferencia(union(a,b), interseccion(a,b));
		}
		
		
		public static boolean sonIguales(ConjuntoTDA a, ConjuntoTDA b) {
			// Todos los elementos de a tienen que estar en b y todos los de b en a
			ConjuntoTA aux1= new ConjuntoTA();
			aux1.inicializarConjunto();
			copiar(a,aux1);
			boolean iguales=true;
			// Verifico que todos los de a estén en b
			while (!aux1.conjuntoVacio() && iguales) {
				int e=aux1.elegir();
				if (!b.pertenece(e))
					iguales=false;				
				aux1.sacar(e);
			}
			if (iguales) {
				// Verifico que todos los de b estén en a
				copiar(b,aux1);
				while (!aux1.conjuntoVacio() && iguales) {
					int e=aux1.elegir();
					if (!a.pertenece(e))
						iguales=false;				
					aux1.sacar(e);
				}
			}		
			return iguales;
		}
		public static void llenar(PilaTDA pila, int n) {
			pila.inicializarPila();
			for (int x = 0; x < n; x++) {
				pila.apilar(x);
			}
		}
		
		public static void llenar(ColaTDA cola, int n) {
			cola.inicializarCola();
			
			for (int x = 0; x < n; x++) {
				cola.acolar(x);
			}
		}
		
		public static void llenarRnd(PilaTDA p, int x) {
			
			Random rnd = new Random();
			for (int y = 0; y < x; y++) {
				int rndNumber = rnd.nextInt(0,x);
				p.apilar(rndNumber);
				
			}
		}
		
	public static void llenarRnd(ColaTDA c, int x) {
			
			Random rnd = new Random();
			for (int y = 0; y < x; y++) {
				int rndNumber = rnd.nextInt(0,x);
				c.acolar(rndNumber);
				
			}
		}

	public static void llenarRnd(ColaPrioridadTDA cp, int x) {
		Random rnd = new Random();
		for (int y = 0; y < x; y++) {
			int rndNumber = rnd.nextInt(0,x);
			int rndPrioridad = rnd.nextInt(0,x);
			cp.acolarPrioridad(rndNumber,rndPrioridad);
		
		}
	}
					
		public static void llenar(ColaPrioridadTDA cola, int n) {
			Random rnd = new Random();
			cola.inicializarCola();
			
			for (int x = 0; x < n; x++) {
				int rndNumber = rnd.nextInt(n);
				cola.acolarPrioridad(x, rndNumber);
			}
		}
		
		public static void llenar(DiccionarioMultipleTDA dm, int n) {
			Random rnd = new Random();
			//dm.inicializarDiccionario();
		
			
			for (int clave = 0; clave < n; clave++) {
				for(int c=0; c < n;c++) {
					int valor = rnd.nextInt(n);
					dm.agregar(clave, valor);
				}
			}
		}
		
		public static void llenar(DiccionarioSimpleTDA d, int n) {
			Random rnd = new Random();
			d.inicializarDiccionario();
			
			for (int clave = 1; clave < n; clave++) {
				int valor = rnd.nextInt(100);
				d.agregar(clave, valor);
			}
		}
		
		
		public static void llenar(ConjuntoTDA conjunto, int n) {
			Random rnd = new Random();		
			for (int i = 0; i < n; i++) {
				int x = rnd.nextInt(n);
				conjunto.agregar(x);
			}
		}
		
		public static void mostrar(ColaPrioridadTDA cola) {
			if (cola.colaVacia()) {
				System.out.println("La cola se encuentra vacia, imposible mostrarla.");
				return;
			}
			
			ColaPrioridadTDA aux = new ColaPrioridadDA();
			aux.inicializarCola();		
			copiar(cola, aux);
			System.out.println("Cola:");
			while (!aux.colaVacia()) {
				System.out.print(" Valor: " + aux.primero() + " Prioridad: " + aux.prioridad());
				aux.desacolar();
				System.out.println("");
			}
			System.out.println();
		}
		
		public static void mostrar(ColaTDA cola) {
			if (cola.colaVacia()) {
				System.out.println("La cola se encuentra vacia, imposible mostrarla.");
				return;
			}
			System.out.print("Cola:");
			ColaTDA aux = new ColaPU();
			aux.inicializarCola();		
			copiar(cola, aux);
			
			while (!aux.colaVacia()) {
				System.out.print(" " + aux.primero());
				aux.desacolar();
			}
			System.out.println();
		}
		
		public static void mostrar(PilaTDA pila) {
			if (pila.pilaVacia()) {
				System.out.println("La pila se encuentra vacia, imposible mostrarla.");
				return;
			}
			System.out.print("Pila:");
			PilaTDA aux = new PilaTI();
			aux.inicializarPila();		
			copiar(pila, aux);
			
			while (!aux.pilaVacia()) {
				System.out.print(" " + aux.tope());
				aux.desapilar();
			}
			System.out.println();
		}
		
		public static void mostrar(PilaTDA pila, String leyenda) {
			if (pila.pilaVacia()) {
				System.out.println("La pila se encuentra vacia, imposible mostrarla.");
				return;
			}
			System.out.print(leyenda +":");
			PilaTDA aux = new PilaTI();
			aux.inicializarPila();		
			copiar(pila, aux);
			
			while (!aux.pilaVacia()) {
				System.out.print(" " + aux.tope());
				aux.desapilar();
			}
			System.out.println();
		}
		public static void mostrar(ConjuntoTDA a) {
			ConjuntoTA aux1= new ConjuntoTA();
			aux1.inicializarConjunto();
			copiar(a,aux1);
			System.out.print("Conjunto :");
			while (!aux1.conjuntoVacio()) {
				int e=aux1.elegir();
				System.out.print(e+" ");
				aux1.sacar(e);
			}
			System.out.println();
		}
		
		public static void mostrar(DiccionarioMultipleTDA dict, String leyenda) {
			System.out.println("Diccionario:" +leyenda );
			/* En los diccionarios, la llave de acceso son las claves
			 * por eso, lo primero que hacemos es leer las claves
			 * y a través de las claves accedemos a los valores
			 */
			
			ConjuntoTDA claves= dict.claves();
			while(!claves.conjuntoVacio()){
				System.out.print("Clave: ");
				int clave=claves.elegir();
				System.out.print(clave);
				System.out.print("; Valores: ");			
				ConjuntoTDA valores = dict.recuperar(clave);
				// Por cada clave, recupero su conjunto de valores
				while(!valores.conjuntoVacio()) {
					int elegido=valores.elegir();
					System.out.print(elegido + ",");
					valores.sacar(elegido);
				}
				System.out.println();
				claves.sacar(clave);
			}		
		}
		public static void mostrar(DiccionarioSimpleTDA d, String leyenda) {
			System.out.println("Diccionario:" +leyenda );
			/* En los diccionarios, la llave de acceso son las claves
			 * por eso, lo primero que hacemos es leer las claves
			 * y a través de las claves accedemos a los valores
			 */
			ConjuntoTDA claves= d.claves();
			while(!claves.conjuntoVacio()){
				System.out.print("Clave: ");
				int clave=claves.elegir();
				System.out.print(clave);
				System.out.println("; Valor: "+d.recuperar(clave));			
				claves.sacar(clave);
			}		
		}
			
	
	public static void conjuntocRepetidos() {
		//Metodo para crear un conjunto con los elementos repetidos de una Pila
			PilaTDA p = new PilaLD();
			p.inicializarPila();
			ConjuntoTDA cj = new ConjuntoLD();
			cj.inicializarConjunto();
			ConjuntoTDA cj1 = new ConjuntoLD();
			cj1.inicializarConjunto();
			
			
			llenarRnd(p, 10);
			mostrar(p);	
			while (!p.pilaVacia()) {
				
				if (!cj.pertenece(p.tope())) {
					cj.agregar(p.tope());
					p.desapilar();
				}
				
				else {
					cj1.agregar(p.tope());
					p.desapilar();
				}
					
			}
			
		}

	public static void dividirPila () {
		// Metodo para para dividir una pila en dos, asumiendo que la pila tiene elementos pares
		PilaTDA p0 = new PilaTF();
		PilaTDA p1 = new PilaTF();
		PilaTDA p2 = new PilaTF();
		
		p0.inicializarPila();
		p1.inicializarPila();
		p2.inicializarPila();
		int elementos=0;
		int x=0;
		llenar(p0, 10);
		elementos = contarElementosPila(p0);
		while (!p0.pilaVacia()) {
			
			if (x<elementos/2) {
				
				p1.apilar(p0.tope());
				
			}
			
			else if(x>elementos/2) {
				p2.apilar(p0.tope());
			}
			
			x++;
			p0.desapilar();
			
		}
		
		//mostrar(p1);
		//mostrar(p2);
		}



	public static void conjuntoRepetidosCola() {
		// Metodo para crear un conjunto con los elementos repetidos de una cola
		ColaTDA c = new ColaLD();
		c.inicializarCola();
		ConjuntoTDA cj = new ConjuntoLD();
		cj.inicializarConjunto();
		ConjuntoTDA cj1 = new ConjuntoLD();
		cj1.inicializarConjunto();
		
		llenarRnd(c, 10);
		mostrar(c);
	while (!c.colaVacia()) {
			
			if (!cj.pertenece(c.primero())) {
				cj.agregar(c.primero());
				c.desacolar();
			}
			
			else {
				cj1.agregar(c.primero());
				c.desacolar();
			}
			
			
			
		}
	}
		

	public static void diccionarioPnCRepetidos(PilaTDA pila1, ColaTDA cola1, DiccionarioSimpleTDA dic) {
		
		// Este metodo cuenta los elementos repetidos de una pila y una cola usando un diccionario simple
		
		PilaTDA pila2 = new PilaLD();
		ColaTDA cola2 = new ColaLD();
			pila2.inicializarPila();
			cola2.inicializarCola();
			llenarRnd(pila1, 10);	
			llenarRnd(cola1, 5);
			copiar(pila1,pila2);
			copiar(cola1,cola2);
			int valor;
			
			while (!pila2.pilaVacia()) {
				
					dic.agregar(pila2.tope(), 0);
					pila2.desapilar();			
			}
				
			while (!cola2.colaVacia()) {
					dic.agregar(cola2.primero(), 0);
					cola2.desacolar();
			}
			while(!pila1.pilaVacia()) {
				
				valor=dic.recuperar(pila1.tope());
				dic.agregar(pila1.tope(), valor+1);
				pila1.desapilar();
			}
			
			while (!cola1.colaVacia()) {
				valor=dic.recuperar(cola1.primero());
				dic.agregar(cola1.primero(), valor+1);
				cola1.desacolar();
			}		
		}
		
		
		
		public static void agrupar (DiccionarioSimpleTDA d, DiccionarioMultipleTDA dm) {
			
			
			ConjuntoTDA claves= d.claves();
			int clave = 0;
			while (!claves.conjuntoVacio()) {
				clave = claves.elegir();
				dm.agregar(d.recuperar(clave), clave);
				
			claves.sacar(clave);	
			}
			
			
		}


	//---------------------------------------------------ARBOLES--------------------------------------------------
	 


	public static boolean existeElementoEnABB(ABBTDA a, int x) {
			boolean retorno=false;
			if (!a.arbolVacio()) {
				if(a.raiz()==x)
					retorno=true;
				else
					if (a.raiz() > x)
						retorno=existeElementoEnABB(a.hijoIzq(),x);
					else
						retorno=existeElementoEnABB(a.hijoDer(),x);		
			}
			return retorno;
				
		}
		
		public static boolean existeElementoEnABB_NR(ABBTDA a, int x) {
			while (!(a.arbolVacio() || a.raiz()==x) ) {			
					if (a.raiz() > x)
						a=a.hijoIzq();
					else
						a=a.hijoDer();		
			}
			return !a.arbolVacio() ;			
		}
		
		
		
		public static boolean esHojaEnABB(ABBTDA a, int x) {
			boolean retorno=false;
			if (!a.arbolVacio()) {
				if(a.raiz()==x && a.hijoIzq().arbolVacio() && a.hijoDer().arbolVacio())
					retorno=true;
				else
					if (a.raiz() > x)
						retorno=existeElementoEnABB(a.hijoIzq(),x);
					else
						retorno=existeElementoEnABB(a.hijoDer(),x);		
			}
			return retorno;			
		}

		public static int calcularProfundidad(ABBTDA a, int x){ 
			// retorna -1 cuando x no se encuentra en el arbol
			int retorno=-1;
			if (!a.arbolVacio())
			{// Si llego a un arbol vacio es porque no encontró un x
				if (a.raiz() == x)
					retorno= 0;		    
			    else 
			    {// retorno debería ser mayor que cero, porque bajo al menos un nivel
			    	if (a.raiz() > x)
			           retorno= calcularProfundidad(a.hijoIzq(), x);
			    	else
			    	   retorno= calcularProfundidad(a.hijoDer(), x);
			    	//Si retorno es -1 es porque no fue encontrado x, entonces retorno -1
			    	retorno=(retorno==-1)?-1:retorno+1; 
			    }
			}		
			return retorno;
		}
		
		public static int contar(ABBTDA a){ 
			int retorno=0;
			if (!a.arbolVacio())
			    retorno= 1+contar(a.hijoIzq())+contar(a.hijoDer()) ; // c=c+1		
			return retorno;
		}
		
		public static int sumar(ABBTDA a){ 
			int retorno=0;
			if (!a.arbolVacio())
			    retorno= a.raiz()+contar(a.hijoIzq())+contar(a.hijoDer()) ; // s=s+a		
			return retorno;
		}
		
		public static int contarHojas(ABBTDA a){ 
			int retorno=0;
			if (!a.arbolVacio())
			    retorno= (a.hijoDer().arbolVacio() && a.hijoIzq().arbolVacio())? 1:0+contar(a.hijoIzq())+contar(a.hijoDer()) ;		
			return retorno;
		}
		
		
		public static int contarHojas1(ABBTDA a){ 
			int retorno=0;
			if (!a.arbolVacio())
				if(a.hijoDer().arbolVacio() && a.hijoIzq().arbolVacio())
					retorno=1;
				else 
					retorno= 0+contar(a.hijoIzq())+contar(a.hijoDer()) ;		
			return retorno;
		}
		
		public static int altura(ABBTDA a){ 
			int retorno=-1;// Si pongo 0 funciona como el simulador
			if (!a.arbolVacio()) {
				int ai=altura(a.hijoIzq());
				int ad=altura(a.hijoDer());
				retorno=1 + ((ai > ad)? ai:ad);
			}
			return retorno;
		}
		public static boolean mismaForma(ABBTDA a1, ABBTDA a2){ 
			boolean retorno;
			if (!a1.arbolVacio() && !a2.arbolVacio()) 		
				retorno= mismaForma(a1.hijoIzq(), a2.hijoIzq()) && mismaForma(a2.hijoDer(), a2.hijoDer());
			else
				retorno=a1.arbolVacio() && a2.arbolVacio();		
			return retorno;
		}



	public class TestArbolInicial {
			public static void main(String[] args) {
				// TODO Auto-generated method stub
				ABBTDA arbol= new ABB();
				arbol.inicializarArbol();	
				arbol.agregarElem(100);
				
				/*
				 *  El método agregar crea e 
				 *  inicializa un arbol 
				 *  a la izquierda
				 */
				arbol.agregarElem(200);
				arbol.agregarElem(50);
				arbol.agregarElem(70);
				arbol.agregarElem(20);
				arbol.agregarElem(150);
				arbol.agregarElem(400);
				System.out.println("raiz:"+arbol.raiz()+ " , "+"Hijo izquierdo:"+arbol.hijoIzq().raiz()+ " , " +"Hijo derecho:"+arbol.hijoDer().raiz());					
				System.out.println("INORDER: Imprimir ordenados de menor a mayor ");					
				inorder(arbol);
				System.out.println("PREORDER: Imprime primero los padres y luego sus hijo izquierdo y luego el derecho");					
				preorder(arbol);
				System.out.println("POSTRDER: Imprimir primero las hojas y luego el padre");					
				postorder(arbol);
			}
			
			
			
			
			public static void preorder(ABBTDA a) {
				if (!a.arbolVacio()) {
					System.out.println(" " +a.raiz());
					preorder(a.hijoIzq());				
					preorder(a.hijoDer());
				}
				
			}
			public static void postorder(ABBTDA a) {
				if (!a.arbolVacio()) {
					postorder(a.hijoIzq());			
					postorder(a.hijoDer());
					System.out.println(" " +a.raiz());
				}
				
			}
			
			public static void inorder(ABBTDA a) {
				if (!a.arbolVacio()) {
					inorder(a.hijoIzq());
					System.out.println(" " +a.raiz());
					inorder(a.hijoDer());
				}
				
			}


		
			
	}



//-------------------------------------------------- GRAFOS -------------------------------

public static int mayorCosto(GrafoTDA g, int v) {
	int max=-1;
	ConjuntoTDA vertices=g.vertices();
	if (vertices.pertenece(v)) {
		vertices.sacar(v);
		boolean primeraVez=true;
		while(!vertices.conjuntoVacio()) {
			int vertice_destino=vertices.elegir();
			if(g.existeArista(v,vertice_destino))
				if (primeraVez) {
					max=g.pesoArista(v,vertice_destino);
					primeraVez=false;
				}
				else
					if (max < g.pesoArista(v,vertice_destino))
						max=g.pesoArista(v,vertice_destino);					
			vertices.sacar(vertice_destino);					
			}
	}			
	return max;
}


public static int mayorCosto1(GrafoTDA g, int v) {
	int max=-1;
	ConjuntoTDA vertices=g.vertices();
	if (vertices.pertenece(v)) {
		vertices.sacar(v);			
		while(!vertices.conjuntoVacio()) {
			int vertice_destino=vertices.elegir();
			if(g.existeArista(v,vertice_destino))				
				if (max < g.pesoArista(v,vertice_destino))
					max=g.pesoArista(v,vertice_destino);					
			vertices.sacar(vertice_destino);					
			}
	}			
	return max;
}

public static int menorCosto(GrafoTDA g, int v) {
	int min=99999;
	ConjuntoTDA vertices=g.vertices();
	if (vertices.pertenece(v)) {
		vertices.sacar(v);			
		while(!vertices.conjuntoVacio()) {
			int vertice_destino=vertices.elegir();
			if(g.existeArista(v,vertice_destino))				
				if (min > g.pesoArista(v,vertice_destino))
					min=g.pesoArista(v,vertice_destino);					
			vertices.sacar(vertice_destino);					
			}
	}			
	return min;
}

public static ConjuntoTDA conjuntoPredecesor(GrafoTDA g, int v) {
	ConjuntoTDA conjResultante=new ConjuntoLD();
	conjResultante.inicializarConjunto();
	ConjuntoTDA vertices=g.vertices();
	if (vertices.pertenece(v)) {
		vertices.sacar(v);			
		while(!vertices.conjuntoVacio()) {
			int vertice_origen=vertices.elegir();
			if(g.existeArista(vertice_origen,v))				
				conjResultante.agregar(vertice_origen);		
			vertices.sacar(vertice_origen);					
			}
	}			
	return conjResultante;
}

public static ConjuntoTDA dobleAdyacente(GrafoTDA g, int v) {
	ConjuntoTDA conjResultante=new ConjuntoLD();
	conjResultante.inicializarConjunto();
	ConjuntoTDA ws;
	ConjuntoTDA xs=g.vertices();
	if (xs.pertenece(v)) {
		xs.sacar(v);			
		while(!xs.conjuntoVacio()) {//Candidatos a x
			int x =xs.elegir();
			if(g.existeArista(v,x)) {	
				ws=g.vertices();			    
			    ws.sacar(v);
				while(!ws.conjuntoVacio()) {//Candidatos a w
					int w=ws.elegir();
					if (g.existeArista(x, w))// Por cada x debo verificar que exista un w
						conjResultante.agregar(w);
					ws.sacar(w);
				}	
			}	
			xs.sacar(x);					
			}
	}			
	return conjResultante;
}



public static ConjuntoTDA aislados(GrafoTDA g) { //DEVUELVE UN CONJUNTO CON TODOS LOS VERTICES AISLADOS DE UN GRAFO G, SIENDO AISLADOS LOS VERTICES QUE NO TIENEN ARISTAS ENTRANTES O SALIENTES
	ConjuntoTDA conjResultante=new ConjuntoLD();
	conjResultante.inicializarConjunto();		
	ConjuntoTDA vertices=g.vertices();
	while(!vertices.conjuntoVacio()) {//Candidatos a aislado
		int v=vertices.elegir();
		boolean aislado=true;
		ConjuntoTDA otros=g.vertices();
		otros.sacar(v);
		while(!otros.conjuntoVacio() && aislado) {//Candidatos a w
			int otro=otros.elegir();
			if (g.existeArista(v, otro) || g.existeArista(otro, v ))// Por cada v debo verificar que sea aislado
				aislado=false;
			otros.sacar(otro);
		}	
		if (aislado)
			conjResultante.agregar(v);
		
		vertices.sacar(v);					
	}
			
	return conjResultante;
}


public static void mostrarGrafo(GrafoTDA g) { //PRINTEA UN GRAFO ENTERO
	ConjuntoTDA V1 = g.vertices();
	while (!V1.conjuntoVacio()) {
		int x1 = V1.elegir();
		V1.sacar(x1);
		ConjuntoTDA V2 = g.vertices();
		while (!V2.conjuntoVacio()) {
			int x2 = V2.elegir();
			V2.sacar(x2);
			if (g.existeArista(x1,x2))
				System.out.print(x1+"->("+g.pesoArista(x1,x2)+")->"+x2+" ");
			else
				System.out.print(x1+"->(0)->"+x2+" ");
		}
		System.out.println("");
	}
}


public static ConjuntoTDA GrafosPredecesores(GrafoTDA g, int v1) { //DEVUELVE TODOS LOS VERTICES PREDECESORES A V, SIENDO PREDECESORES LOS VERTICES QUE TIENEN UNA ARISTA QUE TERMINA EN V
	ConjuntoTDA vertices = g.vertices();
	ConjuntoTDA predecesores = new ConjuntoTA();
	predecesores.inicializarConjunto();
	while(!vertices.conjuntoVacio()) {
		int v2 = vertices.elegir();
		vertices.sacar(v2);
		if(g.existeArista(v2, v1))
			predecesores.agregar(v2);
	}
	return predecesores;
}

public static int GradoVertice(GrafoTDA g, int v) { //DEVUELVE EL GRADO DE UN VERTICE, SIENDO EL GRADO LA DIFERENCIA ENTRE ARISTAS ENTRANTES Y ARISTAS SALIENTES
	////ARISTAS SALIENTES
	int arSalientes = 0;
	ConjuntoTDA vecinosVert = VecinosGrafos(g, v);
	while(!vecinosVert.conjuntoVacio()) {
		vecinosVert.sacar(vecinosVert.elegir());
		arSalientes++;
	}
	//ARISTAS ENTRANTES
	int arEntrantes = 0;
	ConjuntoTDA vertices = g.vertices();
	while(!vertices.conjuntoVacio()) {
		int aux = vertices.elegir();
		vertices.sacar(aux);
		if(g.existeArista(aux, v))
			arEntrantes++;
	}
	return arSalientes - arEntrantes;
}

public static ConjuntoTDA VecinosGrafos(GrafoTDA g, int v) { //DEVUELVE UN CONJUNTO CON LOS GRAFOS VECINOS A UN GRAFO G, SIENDO VECINOS LOS VERTICES A LOS QUE ESTE CONECTADO UN VERTICE V
	ConjuntoTDA vertices = g.vertices();
	ConjuntoTDA vecinos = new ConjuntoTA();
	vecinos.inicializarConjunto();
	
	while(!vertices.conjuntoVacio()) {
		int vert = vertices.elegir();
		vertices.sacar(vert);
		if(g.existeArista(v, vert))
			vecinos.agregar(vert);
	}
	return vecinos;
}

public static ColaTDA inorder(ABBTDA a, ColaTDA c) {
	if (!a.arbolVacio()) {
		inorder(a.hijoIzq(),c);
		c.acolar(a.raiz());
		inorder(a.hijoDer(),c);
	}
	return c;
}


public static ColaTDA metodo(GrafoTDA g) {
	

	ColaTDA c=new ColaLD();
	c.inicializarCola();
	ABBTDA arbol=new ABB();
	arbol.inicializarArbol();
	ConjuntoTDA c1=g.vertices();
	while(!c1.conjuntoVacio()) {
		int numero=g.vertices().elegir();
		/*System.out.println(numero);*/
		arbol.agregarElem(numero);
		c1.sacar(numero);
	}
	
	inorder(arbol,c);
	
	return c;
}





}


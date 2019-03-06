package com.example.rijskviewer;

public class MySingleton
{
    /** Constructeur privé */
    private MySingleton()
    {}

    /** Instance unique non préinitialisée */
    private static MySingleton INSTANCE = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static MySingleton getInstance(MainActivity totoniquetamere)
    {
        if (INSTANCE == null)
        {
            synchronized(MySingleton.class)
            {
                if (INSTANCE == null)
                {   INSTANCE = new MySingleton();
                }
            }
        }
        return INSTANCE;
    }
}
import { useRouter } from "next/router";

export default function Home(){
    const router = useRouter();

    return (
        <div>
            <h1>Bem vindo ao Macros Game</h1>
            <button onClick={() => router.push('/paciente')}>Iniciar</button>
        </div>
    );
}


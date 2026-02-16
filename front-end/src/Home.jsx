import {Table, Col, Row} from 'react-bootstrap'
import {useEffect, useState} from "react";
import './App.css'

let didInit = false;

function Home() {
    const [tableContent, setTableContent] = useState([]);


    const fetchTable = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/getPeople');
            setTableContent(await response.json());
        } catch (e){
            console.error(e);
        }
    }
    useEffect(() => {
        if (!didInit) {
            didInit = true;
            // eslint-disable-next-line react-hooks/set-state-in-effect
            fetchTable();
        }
    }, []);

    const tableData = tableContent.map(person =>
        <tr key={person.id}>
            <td>{person.id}</td>
            <td>{person.name}</td>
            <td>{person.surname}</td>
        </tr>
    )

    return (
        <main>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Name</td>
                        <td>Surname</td>
                    </tr>
                </thead>
                <tbody>
                    {tableData}
                </tbody>
            </Table>
        </main>
    )
}

export default Home

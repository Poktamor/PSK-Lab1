import {Table, Col, Row, Nav, Tabs, Tab} from 'react-bootstrap'
import {useEffect, useState} from "react";
import './App.css'

let didInit = false;
function Jobs() {
    const [tableContent, setTableContent] = useState([[]]);

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

    const tableHead = (
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>JobIds</th>
            <th>SubmarineId</th>
        </tr>
    )

    const tableBody = tableContent.map(person =>
        <tr key={person.id}>
            <td>person.id</td>
            <td>person.name</td>
            <td>person.surname</td>
            <td>person.jobIds</td>
            <td>person.submarineId</td>
        </tr>
    )

    return (
        <main className="w-100">
            <Table>
                <thead>
                {tableHead}
                </thead>
                <tbody>
                {tableBody}
                </tbody>
            </Table>
        </main>
    )
}

export default Jobs

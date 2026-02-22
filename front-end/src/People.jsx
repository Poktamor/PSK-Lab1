import {Table, Col, Row, Nav, Tabs, Tab, Modal, ModalBody, Button, Form} from 'react-bootstrap'
import {useEffect, useState} from "react";
import './App.css'
import PeopleModal from "./PeopleModal.jsx";

// let didInit = false;
function People() {
    const [tableContent, setTableContent] = useState([]);
    const [selectedPerson, setSelectedPerson] = useState({
        id: undefined,
        name: "",
        surname: "",
        jobIds: "",
        submarineId: undefined
    });
    const [showModal, setShowModal] = useState(false);

    const fetchTable = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/People');
            setTableContent(await response.json());
        } catch (e){
            console.error(e);
        }
    }
    useEffect(() => {
        // eslint-disable-next-line react-hooks/set-state-in-effect
        fetchTable();
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

    const handlePersonClick = (person) => {
        if (person == null){
            setSelectedPerson({
                id: undefined,
                name: "",
                surname: "",
                jobIds: "",
                submarineId: undefined
            })
        }
        else {
            setSelectedPerson(person);
        }
        setShowModal(true);
    }


    const tableBody = tableContent.map(person => (
        <tr key={person.id}
            onClick={() => {handlePersonClick(person)}}
        >
            <td>{person.id}</td>
            <td>{person.name}</td>
            <td>{person.surname}</td>
            <td>{person.jobIds}</td>
            <td>{person.submarineId}</td>
        </tr>
    ));


    return (
        <main className="w-100 d-flex flex-column gap-1">
            <Button className="align-self-start" onClick={() => {handlePersonClick(null)}}>Add Person</Button>
            <Table striped hover className="selectableTable">
                <thead>
                    {tableHead}
                </thead>
                <tbody>
                    {tableBody}
                </tbody>
            </Table>
            <PeopleModal selectedPerson={selectedPerson} setSelectedPerson={setSelectedPerson} showModal={showModal} setShowModal={setShowModal} fetchTable={fetchTable}/>
        </main>
    )
}

export default People

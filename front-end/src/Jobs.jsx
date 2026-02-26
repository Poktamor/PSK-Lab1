import {Table, Col, Row, Nav, Tabs, Tab, Button} from 'react-bootstrap'
import {useEffect, useState} from "react";
import './App.css'
import SubmarinesModal from "./SubmarinesModal.jsx";
import JobsModal from "./JobsModal.jsx";

function Jobs() {
    const [tableContent, setTableContent] = useState([]);
    const [selectedJob, setSelectedJob] = useState({});
    const [showModal, setShowModal] = useState(false);

    const fetchTable = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/Jobs');
            setTableContent(await response.json());
        } catch (e) {
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
        </tr>
    )

    const tableBody = tableContent.map(item =>
        <tr key={item.id} onClick={() => {handleRowClick(item)} }>
            <td>{item.id}</td>
            <td>{item.name}</td>
        </tr>
    )

    const handleRowClick = (job) => {
        if (job == null){
            setSelectedJob({
                id: null,
                name: '',
            })
        }else {
            setSelectedJob(job);
        }
        setShowModal(true);
    }

    return (
        <main className="w-100 d-flex flex-column gap-1">
            <Button className="align-self-start" onClick={() => {handleRowClick(null)}}>Add Job</Button>
            <Table striped hover className="selectableTable">
                <thead>
                {tableHead}
                </thead>
                <tbody>
                {tableBody}
                </tbody>
            </Table>
            <JobsModal selectedJob={selectedJob} setSelectedJob={setSelectedJob} showModal={showModal} setShowModal={setShowModal} fetchTable={fetchTable}/>
        </main>
    )
}

export default Jobs
